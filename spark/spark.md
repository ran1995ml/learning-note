# Spark

`Driver` 协调应用执行。`SparkContext` 申请资源创建 `Executor`，创建 `RDD` 生成 `DAG`，`DAGScheduler` 划分 `DAG`，生成 `Stage`，代表可并行执行的一组任务，一个 `Stage` 里的任务组成 `TaskSet` 传给 `TaskScheduler`，又分为 `FIFO` 和 `Fair`，对任务序列化后分发任务到 `Executor` 的一个线程执行。

`DAGScheduler` 划分完 `Stage` 后，先提交没有父 `Stage` 的 `Stage`，提交过程确定任务个数及类型。父 `Stage` 提交完，子 `Stage` 才能提交。

每个转换算子产生一个 `RDD`，逻辑执行图是计算链。实际上多个计算可合并到一个 `RDD`，目标是尽可能减少 `RDD` 的数量，物理执行图是合并 `RDD` 后的结果。

`Stage` 划分算法：从后往前推，遇到 `Shuffle` 依赖断开，遇到窄依赖加入，每个 `Stage` 的任务数由最后一个 `RDD` 的分区数决定。

窄依赖不仅是一对一，还有多对一，都在一个任务中执行。多对一的情况，安排在一个 `Executor` 执行，不会产生 `Shuffle`。

`MapReduce` 的流程简单固定，没有 `pipeline`。`pipeline` 的思想是数据需要用的时候再算。

`Driver` 出现 `action()`，就生成一个 `job`，有多少 `action()` 就有多少 `job`，一个 `Spark` 称为 `application`。遇到 `action()` 触发提交 `job`，从后往前划分 `Stage`。

`Executor` 分配任务：均衡分配、数据本地性优先、失败重试转移到另一个 `Executor`。

`RDD`，弹性分布式数据集，只读。分布式，有分区。弹性，可以放任意数据类型。惰性执行，有助于优化、容错恢复。只读，防止多任务访问的并发安全性，有助于优化容错恢复。

堆内存划分：`Storage` 内存，存储 `cache`、广播变量。`Execution` 内存，放 `shuffle`、`join`、`aggr` 过程的临时数据。预留内存，防止 `oom`。系统预留内存，存储 `Spark` 对象。`Other` 内存，存储转换 `RDD` 需要的数据，如依赖。

堆外内存划分：`Storage` 和 `Execution` 各分一半。

动态占用机制，设定各部分的空间范围，内存都不足时存到磁盘，有一方不足借用其他空间。可让对方让出空间，转移到磁盘。

`RDD` 是记录分区的只读集合，`RDD` 之间存在依赖关系，构成血缘。血缘保证每个 `RDD` 都可被重新恢复。

`HashShuffle`，每个任务每个分区输出一个文件，产生分区数 * 任务数个文件。合并机制优化，共用一个核的任务相同的分区写入一个文件，产生核数 * 分区数个文件。

`SortShuffle`，数据先写入数据结构，达到阈值后写入磁盘，清空数据结构，写磁盘前先排序。最后每个任务将所有临时文件合并，生成一个索引文件，标识各分区的偏移量。

`BypassSortShuffle`，运行条件任务数小于配置阈值，不能是聚合类的算子。小于200不进行排序，因为数据量本身少节省排序的开销。

`Shuffle` 分 `Shuffle Read` 和 `Shuffle Write`。`Shuffle Write` 对数据分区并持久化，持久化是为减少内存的压力，另一方面是容错。`Shuffle Write` 是为了拉数据，什么时候拉，理论上一个任务结束就可以拉，但为了迎合 `Stage`，父 `Stage` 执行完才能执行，选择所有任务执行完再拉。拉来的数据要在内存做缓冲，一次拉取的数据不能太大。`MapReduce` 为让进入 `Reduce` 的数据有序，要等到全部数据 `shuffle` 完成再处理。`Spark` 不要求 `shuffle` 后的数据全局有序，可以边拉边处理。

刚拉的数据存在缓冲区，处理后的数据放在内存+磁盘上，可灵活设置，可设置阈值，内存达到阈值写磁盘。获取文件位置，`Reduce` 会去 `Driver` 询问任务的数据位置，`Map` 任务完成会将存储位置汇报给 `Driver`。

`MapReduce` 和 `Spark` 在 `shuffle` 上的区别：`map()` 计算每区别，对 `combine`，`MapReduce` 先排序再做，`Spark` 可直接在 `HashMap` 上做。`reduce` 端，`MapReduce` 先拉数据，数据量达到一定规模后再合并，`Spark` 边拉边 `reduce`。内存使用上，`MR` 用环形缓冲区存部分输出结果，`combine` 不需要额外空间，`Spark` 用 `HashMap` 合并，输出磁盘需要一定的缓冲区。`reduce` 端 `MR` 需要一部分内存存拉过来的数据，`combine` 和 `reduce` 不需要额外的空间，因为在 `map` 端已经排序。`Spark` 拉数据要缓冲区。

`MR` 总要将 `Map` 端数据写磁盘，`Spark` 可配置内存不足时才写磁盘。

`Shuffle` 过程避免小文件，文件过多要维护过多的元数据，从多个数据读文件会有更多的磁盘IO。

`Shuffle` 过程频繁使用 `HashMap`，设计了两种，全内存的 `AppendOnlyMap`，内存+磁盘的 `ExternalAppendOnlyMap`。数据达到一定阈值会写磁盘。如何判断剩余内存空间，`Map` 用的是一个大 `Object` 数组，估计使用的大小采用的是粗略估计，使用估算结果决定是否要写磁盘。

`Executor` 收到任务后，先反序列化解析，运行任务得到结果送回 `Driver`，若结果很大先存到本地内存+磁盘，由 `blockManager` 管理，把位置发给 `Driver`，`Driver` 需要的时候再自己拉。

为加快 `reduce`，为每个 `reducer` 启动5个并行拉取的线程，拉来的数据先在内存做缓冲，一次不能拉太多，这个空间是5个拉取线程共享的。

`finalRDD` 的结果会返回给 `driver` 汇总。

在序列化上，`Spark` 可以自定义序列化，注重灵活高效。`MapReduce` 依赖固定的 `Writable`，开发复杂适合大规模处理任务。

区别于 `Hadoop` 的重要特性，`cache`。保证要访问重复数据的应用可运行更快。`Cache` 可让重复数据在同一个应用中 `job` 间共享。任务计算得到记录，先判断是否要被 `cache`，需要则传给 `blockManager`，优先存内存，存不下放磁盘。若任务需要读取缓存的 `RDD`，去找 `blockManager` 读取，计算 `RDD` 前先尝试读 `blockManager`。

`Driver` 上有个 `blockManagerMaster`，本地的 `blockManager` 会把自己维护的 `block` 上报给他，其他节点访问会通过他得到位置再访问对应的节点。

运算时间过长运算量过大的 `RDD` 需要 `checkpoint`。`ShuffleMapTask` 的输出放到本地磁盘也算 `checkpoint`。`checkpoint` 机制是等工作结束够开启专门的 `job` 完成，需要被 `checkpoint` 的 `RDD` 要被计算两次，建议加上 `cache` 避免二次计算。

每个 `job` 结束调用 `doCheckpoint()`，`finalRDD` 顺着链路回溯，检查到 `checkpointRDD` 做标记，启动 `job` 完成，结果存 `HDFS`。

`MR` 的任务太傻，中途出错要完全重新执行，如 `shuffle` 了一半的数据存到磁盘，重新运行仍要重新 `shuffle`。

广播，将数据从一个节点发到其他各个节点。广播只能是只读变量，因为涉及到一致性问题，若可更新，被读取后其他节点是否要一块更新，如何同步会引入复杂度。用于静态查找表大型配置。广播的对象是节点而不是任务，因为一些任务共享一个节点。`Driver` 先建一个文件夹存放广播数据，声明广播变量时将数据写入文件夹同时写入自己的 `blockManager`。函数用到广播变量，会将 `block` 信息一起序列化加入任务。在 `executor` 反序列化时，先去本地 `blockManager` 拉，拉不到询问 `Driver`。

只存在 `Driver` 端，都向 `Driver` 拉不能做到负载均衡。若有 `Executor` 拉取了数据，通知 `Driver` 可传递数据给其他节点。

累加器，任务侧只累加，读取只在 `Driver`。
