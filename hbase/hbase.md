# HBase

## 架构设计

一行数据包含唯一标识 `rowkey`、多个列及对应的值。列由列族+列名组成。`cell` 由 `(row,column,timestamp,type,value)` 组成，键是 `(row,column,timestamp,type)`。写入 `cell` 默认会分配一个时间戳作为版本，也可以用户写入时指定。

特点：多维，键是复合数据结构。稀疏，列的大多数行是空值，不需要填充，理论上列可无限扩展。排序：同一个文件中的键值是有序的。分布式，所有 `Map` 分布在整个集群中。

按列族存储在不同目录，一个列族的数据存储在一起。列族式存储介于行式存储和列式存储之间，只设一个列族包含所有列，等同于行式存储。设置大量列族，每个列族只有一列，等同于行式存储。

`Master`：处理管理类请求，如建表、修改表、权限操作、合并数据分片等。管理集群所有 `RegionServer`，包括宕机恢复、`Region` 负载均衡、`Region` 迁移。检查过期的 `HLog` 并删除。

`RegionServer`：响应 `IO` 请求。

## 数据结构

一个列族本质是一棵 `LSM` 树，分内存和磁盘部分，内存是跳表，磁盘中存了额外的布隆过滤器。数据有 `type` 属性表示操作类型，说明了 `LSM` 树存储的不只是数据，而是每次的操作记录。

## 依赖服务

 `Zookeeper` 作用：`meta` 表所在 `RegionServer`。`Master` 主从选举。`Region` 迁移。所有 `RegionServer` 信息。表锁。集群所有表信息。

## 客户端

`Configuration` 封装配置，`Connection` 对象连接集群，请求分发到不同节点，一个进程对一个集群只需建一个连接。建多个连接是为提高客户端吞吐量，请求时间主要花在服务端，可以不需要用连接池。`Connection` 还缓存元数据。

一张表数据由多个 `Region` 组成。操作数据先确定 `Region`，再确定对应的 `RegionServer`。

`hbase:meta` 表存放所有 `Region` 信息，只有一个列族 `info`，一个 `Region`，确保多次操作的原子性，因只支持 `Region` 级别的事务。一个 `rowkey` 对应一个 `Region`，由 `(TableName, StartRow, Timestamp, EncodedName)` 组成。

查找元数据表一般反向查找，若查找的 `key` 大于所有 `StartRow` 可快速找到最大的 `StartRow`。该 `Region` 的热点问题，客户端缓存减小压力。

采用 `LSM` 树适合写多读少，更新删除也被视为写入操作。写入数据，客户端发送请求到 `RegionServer`，先写入 `WAL`，再写入 `Region` 列族对应的 `MemStore`。`MemStore` 超阈值写入 `HFile`。

客户端从元数据表根据 `rowkey` 找到归属的 `Region`，向对应的 `RegionServer` 发请求，每个分组对应一次 `RPC` 请求。`RegionServer` 处理请求，申请锁保证更新原子性，更新时间戳，写入 `WAL`，写入 `MemStore` 释放锁。`MemStore` 触发条件溢写到 `HFile`，写入过程构建数据块、索引块、布隆过滤器。

`BuildLoad` 用 `MR` 将写入集群数据转换成 `HFile`，加载到集群目录。不需要发写请求给 `RegionServer`。

读操作分 `get` 和 `scan`，`get` 根据 `rowkey` 查一行记录，`scan` 根据 `startkey` 和 `stopkey` 查满足条件的多行记录。`scan` 可能扫描全表，大量数据占用网络带宽，客户端无法缓存，拆分成多次 `RPC` 请求。

每个 `Region` 都是一个独立的存储引擎，可将每个子区间请求发送到对应的 `Region` 处理。

## RegionServer

`HLog` 保证数据写入可靠性，实现故障恢复和主从复制。写入的数据先追加写入 `HLog`，再写入 `MemStore`。

多个 `Region` 共享一个 `HLog`。日志单元表示一次行级更新的最小追加单元，为保证行级事务原子性，一个行级事务的写入操作表示为一条记录。`HLog` 和用户数据都存在 `HDFS` 指定目录。后台线程每隔一段时间进行日志滚动，新建一个日志文件，方便过期日志能以文件形式直接删除。写入数据从 `MemStore` 落盘，日志失效。每隔一段时间删除所有失效日志文件，若在进行主从复制则等待。

每个 `Region` 包含所有列族数据，不同列族数据存在不同的 `Store`。每个 `Store` 由一个 `MemStore` 和多个 `HFile` 组成。写入 `HLog` 后写入 `MemStore`。当 `MemStore` 数据超阈值写磁盘，生成一个新的 `HFile`。缓存最近写入的数据。

`MemStore` 有两个跳表实现，写入一个跳表，数据量超过阈值另一个跳表接收请求，写满的跳表组织落盘。不同的 `MemStore` 共享内存容易 `FullGC`，对内存分块改善。

`HFile` 包含数据块、索引数据块、布隆过滤器。

`BlockCache`，读取数据先检查 `Block` 是否在 `BlockCache`，不存在从 `HFile` 加载到 `BlockCache`。一个 `RegionServer` 只有一个，默认使用 `LRUBlockCache`，全部数据放到堆内存，`GC` 会是个问题，其他方案允许部分数据存在堆外。

## Compaction

合并一个 `Region` 的一个 `Store` 的部分 `HFile`。从待合并的文件读出 `KV`，排序后写入新文件。是 `LSM` 树结构特有的操作。

`MinorCompartion`：选部分小的、相邻的 `HFile` 合并。`MajorCompartion`：合并一个 `Store` 所有 `HFile`，同时删除被删除数据、过期数据、版本号超过设定数据。

`HFile` 过多会增加查询 `IO` 带来读延迟，合并小文件能稳定读延迟。副作用是重写文件带来很大的带宽和 `IO` 压力。

触发时机：`MemStore` 每次写磁盘后，检查所有 `Store`，文件数超过阈值触发合并。后台线程定期检查 `Store` 是否需要合并。

选择 `HFile`，排除大文件，优先选分裂产生的文件。根据选出的待合并文件数选择线程池执行。读取待合并 `HFile` 数据做归并排序，写到临时文件目录，将临时文件移到数据目录，记录 `HLog`，删除合并前的文件。

若合并过程压力太大，会自适应降低吞吐量。

## 负载均衡

负载均衡，保证每个节点的分片数相同。分片迁移是 `Region` 迁移，操作轻量，不需要移数据，只需要迁移读写服务。

`unassign`， `Region` 从源 `RegionServer` 下线，`Master` 生成事件更新到 `Zookeeepr`，该 `Region` 状态改为 `PENDING_CLOSE`，发送关闭命令给 `RegionServer`。`RegionServer` 收到请求生成事件更新到 `Zookeeper`，`Master` 监听到变化更新 `Region` 状态 `CLOSING`。 `RegionServer` 关闭 `Region`，若 `Region` 在写磁盘或合并则等待，关闭完成更新到 `Zookeeper`，`Master` 更新状态 `CLOSED`。

`assign`，`Region` 在目标 `RegionServer` 上线。`Master` 更新 `Zookeeper`，状态改为 `PENDING_OPEN`，发送命令到 `RegionServer`。收到命令后 `RegionServer` 更新事件到 `Zookeeper`，`Master` 监听到变化，更新状态为 `OPENING`。`RegionServer` 执行打开操作，初始化服务，生成事件到 `Zookeeper`。`Master` 监听到状态改为 `OPEN`。

`Master` 维护 `Region` 在整个操作过程中的状态变化。`RegionServer` 操作命令。`Zookeeeper` 存储操作中的事件。

`meta` 表只存 `Region` 所在的 `RegionServer`，不存储中间状态。`Master` 内存保存所有 `Region` 信息，状态来自 `Zookeeper` 的通知，会有些滞后。

`Region` 合并，某个 `Region` 的数据过期变为空闲，合并空闲 `Region`，客户端发请求从 `meta` 表删除。

`Region` 分裂触发，一个 `Region` 中最大 `Store` 大小超过阈值触发，但是对小表不友好，后改成根据 `Region` 个数动态调整阈值。分裂先找 `Region` 中最大 `Store` 最大文件最中心 `Block` 的首个 `rowkey`。

分裂过程包装成一个事务：准备阶段，初始化两个子 `Region`，设定表名、`startkey`、`endkey`。执行阶段，修改 `Zookeeper` 存的状态为 `SPLITING`，`Master` 监测到修改 `Region` 状态，新建临时文件夹 `.split`，关闭父 `Region` 创建子文件夹，指向父 `Region` 的文件。分裂完成后，子 `Region` 文件夹拷贝到根目录，形成新的 `Region`。父 `Region` 通知 `meta` 表下线，不会立即删除，会标记 `split` 状态并记录子 `Region` 的地址。子 `Region` 开启后通知 `meta` 表。

若执行阶段异常，则回滚，根据子阶段清理垃圾数据。分裂不涉及数据移动，分裂成本不高，子 `Region` 文件仅保存引用。`Major` 合并触发父 `Region` 数据才迁移到子目录。当子 `Region` 不存在引用文件父 `Region` 才被删除。

减少分裂：建表时预分区。

## 宕机恢复

`RegionServer` 宕机异常：`FullGC`、`HDFS` 异常。

`Master` 负载负载均衡，响应创建删除修改表请求，不直接处理用户读写请求，整体负载不高。会配置高可用。

`RegionServer` 宕机，`Master` 通过 `Zookeeper` 监测到，会将宕机 `RegionServer` 上的所有 `Region` 分配到其他 `RegionServer`，再分局 `HLog` 恢复。





