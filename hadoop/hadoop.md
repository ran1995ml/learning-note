# HDFS

主从架构，`NameNode` 管理文件系统的操作，管理文件的元数据信息，一个文件的数据块、数据块的偏移量、所在 `DateNode`。`DataNode` 存储数据。

上传数据前要先切块，这样设计不仅仅是为了契合 `MapReduce`，还为了数据能高效存储，分散在多个节点避免访问时的单点瓶颈。

`Editlog` 记录每次更新操作，`FsImage` 保存元数据快照，用于重启后快速恢复。更新快照成本太高，每次更新先记录 `Editslog`，再更新内存，保证持久性。用 `FsImage` 恢复元数据，需要与 `Editslog` 合并保证最新。`Editslog` 若太大，启动合并成本太高，所以设计了检查点机制，当 `Editslog` 大小到一定阈值触发一次合并。

单 `NameNode` 有单点问题，高可用引入两个 `NameNode`。为保证两个节点元数据一致，引入 `JournalNode`。每次 `activeNameNode` 做更新操作，将 `Editslog` 写入 `JournalNode`，`standbyNameNode` 监测到条数变化，读取更新元数据。

`JournalNode` 本身是个集群，`NameNode` 向所有 `JournalNode` 写入，过半的节点写入成功就算成功。读日志获取每个 `JournalNode` 的写入状态，读取最新版本的日志。

`NameNode` 掌握整个系统的元数据，地位很关键，负载较高。为减轻 `NameNode` 负担，用新角色 `SeconderyNameNode` 承担该职责，高可用模式由 `standbyNameNode` 执行，触发检查点后 `activeNameNode` 将 `FsImage` 发送给 `standbyNameNode`。

`DataNode` 加入后，会主动向 `NameNode` 发送心跳，高可用模式会向两个 `NameNode` 发送，包含状态信息，如存储、负载、数据库等消息。若 `NameNode` 长时间收不到心跳，认为不可用，会将该节点的副本转移到其他节点。

客户端上传文件，先验证用户权限、目录是否有效。验证通过创建元数据，返回块的 `DataNode` 列表。客户端只和第一个 `DataNode` 建立连接，副本的复制会在 `DataNode` 间建立管道传输。切文件后请求 `DataNode` 发送数据，每上传成功发消息给 `NameNode`，`NameNode` 确认后更新元数据，选择下一个数据块的位置给客户端。

为确保数据一致性，上传时会计算校验和保存在 `DataNode` 上。客户端读取或副本复制读取文件时，会计算校验和，若计算错误说明该块损坏，返回给 `NameNode`，`NameNode` 会返回新的 `DataNode`，将移除损坏数据块的元数据信息，等待下次轮询检测到副本不足重复制。

若所有副本损坏，尝试计算纠删码修复。若开启远程备份，用 `Hadoop DistCP` 尝试恢复。

客户端读文件，给 `NameNode` 发送请求获取块的位置，选择距离自己最近的 `DataNode` 读取数据。

高可用模式，客户端可通过 `Zookeeper` 获取 `Active NameNode` 发送请求。为避免高可用脑裂，只能有一个 `NameNode` 向 `JournalNode` 写日志。每产生一次 `Active NameNode` 选举，`epoch number` 增加。当 `NameNode` 假死尝试向 `JournalNode` 写日志，发现自己的轮次小于当前轮次，自动切换成 `Standby`，返回错误给客户端，客户端再请求到 `Active` 节点。

# Yarn

主从架构，`ResourceManager` 和 `NodeManager`。`ResourceManager` 掌握整个集群的资源情况，监控 `NodeManager` 包括负载、资源使用。`NodeManager` 加入后定期向 `ResourceManager` 发送心跳，上报自身的资源如 `CPU`、内存、资源使用率。`ResourceManager` 接收客户端请求，根据集群的资源分布调度。

提交一个应用会创建对应的 `ApplicationMaster` 为其申请资源，监控任务的情况。`Container` 对是节点上的资源、环境变量、参数的封装，`ApplicationMaster` 也运行在 `Container` 里，为保证资源模式不互相干扰，一个 `Container` 只运行一个进程。

客户端提交任务给 `ResourceManager`，先为其启动 `ApplicationMaster`，根据任务需要的资源情况，向 `ResourceManager` 申请资源，根据 `NodeManager` 的资源使用情况，`ResourceManager` 返回 `NodeManager` 列表。`ApplicationMaster` 请求 `NodeManager` 创建任务，并随时监控任务的运行情况。所有任务执行完毕，`ApplicationMaster` 返回执行结果给客户端，向 `ResourceManager` 申请注销。

若 `jar` 在客户端本地，会上传到 `HDFS` 供其他节点使用。

提交方式：`client` 模式，在客户端启动，客户端负责监控任务运行状态，收集日志。`cluster` 模式， `ResourceManager` 选择容器创建 `ApplicationMaster`。具体客户端能不能离线，看客户端的处理能力，有些应用的客户端离线应用仍然能运行，只是不能监控日志。需要交互式、测试环境用 `client`，生产一般用 `cluster`。用 `client` 收集大量信息，对 `client` 的机器会有影响。

高可用，只能有一个 `ResourceManager` 响应请求，客户端有代理借助 `Zookeeper` 找到 `active` 的 `ResourceManager`。

`ApplicationMaster` 重启，若有 `checkpoint`，会从中恢复状态，不必重新执行。

`ResourceManager` 的 `Scheduler` 组件实现了多种调度策略：`FIFO`，所有任务提交到一个默认队列，按提交顺序执行。`Capacity`，支持多租户多队列，以 `FIFO` 调度，每个队列配置一定量的资源，可设置最小容量和最大容量，当某个队列资源不足可动态获取其他队列未使用的资源，可设置优先级，高优先级的任务优先执行。`Fair`，目标是尽可能减少等待的任务，提高资源利用率，可设置多队列，但共享资源，已经运行的任务可释放资源给等待的任务，可设置任务优先级。

# MapReduce

数据处理格式为 `KV`，分为 `Map` 和 `Reduce` 两个阶段。`Map` 端读取分片，分片个数等于 `Map` 任务个数。每个 `Map` 在本地独立运行，互不影响，处理过的数据写入缓冲区，当缓冲区使用超过80%，写到磁盘，写之前会按分区和键排序，生成索引，最后会用归并排序将小文件合并成一个大文件。

`Map` 任务完成会通知 `Reduce` 任务，`Reduce` 任务拉取要处理的分区数据到内存，内存达到一定使用率写到磁盘，处理磁盘文件得到最终结果。

`Shuffle`，`Reduce` 从 `Map` 拉数据的过程，性能瓶颈，会产生大量磁盘 `IO` 和网络 `IO`。

`Combiner`，减少 `Shuffle` 过程传输的数据量，提高性能。在 `Map` 端写磁盘前合并数据，合并小文件时合并数据。`Reduce` 拉取数据可做一次合并。

通常一个 `Map` 任务对应一个数据分片，小文件过多会导致 `Map` 任务过多，造成资源浪费。`MapReduce` 的设计初衷是为了处理大文件。解决方法：减少小文件个数，提供了一种数据读取方法，将小文件从逻辑上合并成一个文件交给 `Map` 任务读取。

二次排序，自定义分区函数实现组合 `key`。全排序，只能保证一个 `Reduce` 处理的数据有序，只有一个 `Reduce` 执行效率又太低，自定义分区函数，将 `key` 有序分配给 `Reduce`，避免数据倾斜，可先用采样函数对某几个数据分片预采样，得到数据分布，按数据分布均匀分配给 `Reduce`。

# Hive

解决 `MapReduce` 实现过于复杂的痛点，用 `SQL` 实现 `MapReduce` 任务执行。

`Hive` 将 `HDFS` 的结构化数据，行式 `CSV`，列式 `ORC`、`Parquet`，键值 `Avro` 映射成数据表，表的元数据通常存储在 `MySQL`。元数据包括对表的描述、分区信息、数据的路径、列及类型、数据格式等。

客户端提交给任务给 `Driver`，解析 `SQL` 生成抽象语法树，查询元数据库解析语义，转换为逻辑执行计划，将 `Join`、`GroupBy` 等操作转换为 `Operator`，优化器优化如列裁剪、`Join` 顺序优化，生成物理执行计划包含了 `Map` 和 `Reudce` 提交给 `Yarn` 执行。

分区，将数据按照某个字段划分到不同的子目录，减少扫描的数据量。分为动态分区和静态分区，静态分区需要显式指定分区列的值，动态分区可根据某个值自动创建分区，需要在非严格模式下开启。

分桶，某个分区的文件按某字段的哈希值分成多个文件，一个文件是一个桶，只能在创建表时指定，用于加速 `Group by` 和 `Join`。

视图，封装一组查询语句，本身不存储数据。物化视图存储数据，但需要借助第三方工具实现。视图解决的痛点：简化复杂查询、提供权限控制只能访问部分数据。

加载数据方式：通过 `Load` 指定文件路径加载，通过 `Insert` 语句插入。

内部表：数据和元数据全部由 `Hive` 管理，数据存放在 `Hive` 配置的目录，删除表数据一起被删除。外部表：数据没有存放在 `Hive` 管理的目录下，只管理元数据，删除表数据不会被删除。

读时模式，插入数据时不会检查类型。插入字段少于表字段、插入字段转换列类型失败，若没有 `NOT NULL` 约束，用 `NULL` 填充。

排序关键字：`Order by`，全局排序，只有一个 `Reduce`。`Distributed by`，按某个字段 `Reduce`。`Sort by`，`Reduce` 内部排序。

严格模式，防止用户恶意操作做的限制。`Order by` 必须加 `limit`。查询分区表必须指定分区。不可进行笛卡尔积查询。不可用动态分区。

数据倾斜优化，主要发生在 `Group By` 和 `Join`。减少扫描的数据量，提前用 `Where` 过滤数据。开启 `Map` 端 `Join`，小表 `Join` 大表，将小表广播到各个节点做 `Join` 操作。根据业务字段特点过滤出热点数据单独处理，或对有热点数据的字段加盐打散，处理后再还原。无法避免大表 `Join` 大表，尽可能在 `Join` 前过滤掉不需要的数据。开启负载均衡，第一个 `job` 读取数据后，将数据随机分发到各个 `Reduce`，打散后再执行处理逻辑。
