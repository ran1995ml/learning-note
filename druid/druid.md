# Druid

时序数据库，存储数据必须指定时间。按 `DataSource` 存储，每个 `DataSource` 中的数据按时间划分成 `Segment`。

存储数据的基本单位是 `Segment`，一个 `Segment` 的唯一标识：`DataSource` + `Interval` + 版本 + 分片ID。`Segment` 内部是列式存储，列的类型有数值类型和字符串类型，做了压缩，默认是 `LZ4`，用滑动窗口匹配重复值，压缩率不高但读取非常快。字符串类型占空间较大，提供了字典，将该列的每个字符串值映射成整型，对于 `null` 是0。根据字典用列表表示字符串列。

`DataSource` 和传统数据库的表结构不一样，表 `schema` 是动态的，`DataSource` 的 `Segment` 可有不同的 `schema`，不影响查询。定义 `DataSource` 的 `schema` 要指定指标和维度，维度通常是字符串类型，指标是数值类型，指标需要指定类型如 `longSum`，查询时选择特定的 `QueryRunner` 执行聚合。维度支持自动发现，默认将指标之外的列转换为维度，很适合动态列。

维度通常作为过滤和聚合条件，为支持快速过滤聚合，每个指标列为维度列的每个值创建了 `bitmap`。当维度过多且是高基维，`bitmap` 存储空间会膨胀非常快，且高基维的 `bitmap` 很稀疏。为节省存储空间，用 `roaringBitMap` 压缩，自适应选择数据结构存储，将数据分块，每个块根据数据分布自适应选择数据结构，数据较稀疏直接用整型数组，稠密用16位位图，连续分布用区间表示。

一个 `Segment` 分成多个文件，每个文件只存一个列，一个列可能存为多个文件，每个文件二进制存储。每个列文件对对应的索引文件，包含偏移量、列信息。默认列会用 `LZ4` 压缩，字典提供了对字符串的映射，当有高基维需要压缩字典，压缩字典新版本采用了 `front` 压缩。该算法应用前缀匹配，在前缀高度相似的情况下能有较高的压缩比，缺点是压缩后不适合更改，很契合 `Druid` 这种以查询为主批量更新的 `OLAP` 数据库。

数据存在哪？存在 `DeepStorage`，可以是 `HDFS`、`S3`、`GCS`。数据写进 `DeepStorage` 才算写入成功。

一个 `OLAP` 数据库要提供的服务：数据写入、数据查询。整个架构划分了多个角色来稳定提供这两个服务。

数据写入，每次写入通过创建任务执行，最终写入到 `DeepStorage`。任务创建在哪？`MiddleManager` 这类角色来承担。大规模数据写入单机肯定不行，`MiddleManager` 需要做成集群，`Druid` 用的是进程模型，`MiddleManager` 上会创建一个进程执行任务，叫 `Peon`。这个进程创建在哪个 `MiddleManager` 上，创建之后如何知道它的状态，何时执行完成，所以需要一个 `Master` 统一管理，叫 `Overlord`。

`Overlord` 会维护集群中所有 `MiddleManager` 的状态，包括有多少个 `slot` 可用来创建任务。提交任务请求到 `Overlord`，创建好任务对象加入到 `TaskQueue`，`TaskRunner` 依次处理 `TaskQueue` 的任务，按照策略选择合适的 `MiddleManager` 请求创建任务，默认策略是选择 `slot` 剩余最多的 `MiddleManager` 来创建。若某个 `Worker` 任务执行失败次数过多，会暂时加入黑名单。

任务加到 `TaskQueue`，先更新元数据库，任务状态初始化为 `PENDING`。

服务直接采用 `http` 通信，发送创建任务请求到 `MiddleManager`。这个过程是异步的，`Worker` 会选择 `slot` 执行任务，`slot` 是对 `CPU` 抽象，物理层面是 `MiddleManager` 本地目录，配置目录下会有 `slot1`、`slot2` 多个子目录一个子目录对应一个 `slot`。`MiddleManager` 启动时会根据配置的 `slot` 个数创建目录，且创建任务时会用本机的 `CPU` 核数 / `slot` 数指定 `CPU` 数，至少为1个，否则启动时会抛异常。这个目录可用于任务写临时文件，目录会有多块盘，`slot` 目录会均匀创建在各个盘目录下，为保证各个盘的数据负载均衡，会均匀分配 `slot` 给任务。

发送请求到 `Worker`，如何算创建成功？写入任务 `Spec` 到本地的 `assignTasks` 目录，写入成功返回 `Overlord` 创建成功。`Overlord` 会更新任务状态为 `RUNNING`。写操作不是原子性的，如果中途失败，会读取到错误的 `Spec`。为保证原子性，先写到 `tmp` 目录，写成功后转移到 `assignTask` 目录。有持久化，即使 `worker` 重启后，依然可以从 `assignTask` 获取执行。创建子进程 `Peon`，执行摄取数据逻辑，任务创建完成再把 `Spec` 写入到 `restore.json`，以便崩溃后尝试恢复。

`TaskRunner` 会通过 `zk` 监听 `Peon` 的状态，`TaskQueue` 注册了 `listener` 到 `TaskRunner`，任务完成后，会更新任务状态到元数据库。

任务分流式摄取和批式摄取。批摄取代表性的从 `HDFS` 写入 `Druid`。既然是从 `HDFS` 读数据，`Druid` 是利用 `MR job`，`Druid` 创建的任务只是作为一个客户端提交 `MR` 并监控 `MR` 的状态。数据写入到 `DeepStorage` 算写入成功。

流式摄取，消费 `Kafka`，包装 `Kafka` 的消费者客户端。`Druid` 消费 `Kafka` 并不向 `Kafka` 提交偏移量，而是由自己管理偏移量、管理分区分配、任务创建。这些过程的管理交给 `Supervisor`，是 `Overlord` 里的一个线程，每个 `Topic` 的消费对应一个 `Supervisor`。

`Supervisor` 做的几件事：发现分区，要消费要知道有哪些分区，时刻维护当前分区的一个视图。创建时会指定任务数，一个任务是一个消费者组，`Druid` 里定义成任务组，任务组ID从0一直到任务数。分配分区，用分区数取余任务数，根据结果分配给对应的任务组。

创建任务，首先查每个分区有哪些任务消费。任务创建后会更新到元数据库，查元数据库获取该表对应的流式任务。任务分配到 `Worker` 运行，定期从 `Worker` 获取任务的状态。若某个分区消费的任务数不足，会提交给 `TaskQueue` 创建任务。

所以，他的流程简单概括为发现分区、发现任务、创建任务。任务在 `Worker` 创建后，根据分配的分区，用消费者 `pull` 数据到自己的堆内存，
