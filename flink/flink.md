# Flink

架构和 `Spark` 很像，`JobManager` 申请资源分发任务，`TaskManager` 执行任务，汇报任务情况给 `JobManager`。区别是 `TaskManager` 可以预先创建，不像 `Executor` 和应用绑定在一起，`TaskManager` 下的 `slot` 可运行不同应用的子任务。

`slot` 用于资源隔离，控制一个 `TaskManager` 能接受的任务数。若子任务属于一个 `job`，可共享 `slot`。只是逻辑上隔离，只隔离内存，表示了并行度，和 `TaskManager` 绑定。

基本单位是 `Stream`，类似 `RDD`，互相转换。懒执行，遇到 `execute()` 才触发构建数据流图。`keyBy` 生成 `KeyedStream`，

流元素分类：数据记录 `StreamRecord`，表示一条事件，包含数据本身、时间戳。`LatencyMarker`，近似评估延迟，在 `source` 创建向下游发送，绕过处理逻辑，在 `sink` 评估整个 `DAG` 流转事件，包含创建的时间戳、编号。`Watermark`，一个时间戳，告诉算子所有事件早于等于该值的记录都到达，用于触发窗口计算。`StreamStatus`，通知任务是否继续收到上游记录，在 `source` 生成。

时间类型：事件时间、处理时间、摄取时间。窗口类型：计数窗口、时间窗口、会话窗口。

窗口的主要作用是把无限流切割为有限数据块。对 `KeyedStream` 开窗，窗口计算在多个并行子任务上同时执行。 `DataStream` 开窗，只能在一个任务执行。`WindowAssigner` 维护窗口，属于该窗口的第一个元素到达创建窗口，时间超过窗口结束时间加上指定允许延迟时间删除窗口。触发器指定什么条件触发窗口函数，可用定时器来触发。

`key` + 时间对应一个定时器，定时器也会做 `checkpoint`，加入到队列维护，事件时间和处理时间分别对应两个队列。

解决数据乱序：`Watermark` + `Allow Lateness` + `Side Output`

`Watermark`，结合窗口处理事件乱序，用于事件时间处理事件。在 `source` 生成发送到下游。两种实现，周期性生成和对每个事件尝试生成，生成的值需要是递增的才能发到下游。多流汇聚，选择最小的 `Watermark` 发到下游。

内存管理把堆和堆外内存划成 `MemorySegment`，访问 `MemorySegment` 上又抽象出一层内存页。任务算子处理完数据交给下游，内存对象是 `Buffer`，网络传输抽象，包装成 `MemorySegment`。每个 `TaskManager` 有自己的 `BufferPool` 管理 `Buffer`。`MemoryManager` 管理内存，只使用堆外内存，批处理用于排序、缓存中间结果，流处理作为状态后端内存。管理范围是 `slot`。

状态用来保存中间计算结果或缓存数据，需要保证 `Exactly-Once`。示例：求和、去重、模式监测。`StateDescriptor` 描述状态，通过其从状态后端获取状态实例，没有则创建新的状态。广播状态必须是 `MapState` 类型。状态后端类型：纯内存，内存+文件，`RocksDB`。

算子状态，作用范围限定算子任务。键值状态，根据 `key` 访问更新。`key` 哈希值相同的元素，值不同被分到一个分区，键值状态在对应算子任务中可能有多个。可设置 `TTL`，只对本地任务可见，本地化管理减少网络传输延迟。

`checkpoint` 定期在数据流上生成 `barrier`，算子收到 `barrier` 生成状态快照，传到下游直到 `sink`。异常会根据最近快照恢复状态。`savepoints`，手动触发 `checkpoint`。状态后端类似 `Spark` 的 `BlockManager`。每个 `barrier` 对应一次 `checkpoint`，编号对应顺序，若较大编号的 `barrier` 先到，先缓存等待。

保证端到端的一致性，`checkpoint` 完成后提交偏移量，`checkpoint` 完成后提交 `sink` 事务。
