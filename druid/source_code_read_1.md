# Service启动

用 `Airline` 解析命令行参数映射到对应的 `Runnable`，调用 `run()` 启动服务。

```java
public class Main
{
  public static void main(String[] args)
  {
    //解析命令行
    final Runnable command = cli.parse(args);
    if (!(command instanceof Help)) {
      //注入command对象
      injector.injectMembers(command);
    }
    //启动进程
    command.run();
  }
}
```

<img src="picture/View.png" alt="runnable" style="zoom:50%;" />

服务启动使用了模板方法模式。服务的启动：

```java
public abstract class ServerRunnable extends GuiceRunnable
{
  @Override
  public void run()
  {
    final Injector injector = makeInjector(getNodeRoles(getProperties()));
    final Lifecycle lifecycle = initLifecycle(injector);

    try {
      lifecycle.join();
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
```

# Service生命周期管理

`Lifecycle` 管理服务的生命周期，有4个阶段：

- `INIT`：初始化 `log4j`；
- `NORMAL`：完成大部分对象的初始化；
- `SERVER`：完成服务器对象初始化，如 `jetty`；
- `ANNOUNCEMENTS`：通知集群该服务可用。

```java
public class Lifecycle
{ 
  public enum Stage
  {
    INIT,  //专门用于log4j的初始化
    NORMAL, //对大部分对象的配置，如zkClient，MetadataConnector的初始化
    SERVER, //适用于所有服务器对象
    ANNOUNCEMENTS //向集群宣告
  }
  
  private enum State
  {
    NOT_STARTED, //start()调用前
    RUNNING,  //start()调用后，stop()调用前
    STOP  //stop()调用后
  }
  
  //主要在NORMAL阶段，初始化其他对象
  public interface Handler
  {
    void start() throws Exception;

    void stop();
  }
  
  //大部分对象，如zkClient，MetadataConnector注入时调用此方法，在NORMAL阶段调用，添加handler
  public void addHandler(Handler handler, Stage stage)
  {
    if (!startStopLock.tryLock()) {
      throw new ISE("Cannot add a handler in the process of Lifecycle starting or stopping");
    }
    try {
      if (!state.get().equals(State.NOT_STARTED)) {
        throw new ISE("Cannot add a handler after the Lifecycle has started, it doesn't work that way.");
      }
      handlers.get(stage).add(handler);
    }
    finally {
      startStopLock.unlock();
    }
  }
  
  public void join() throws InterruptedException
  {
    ensureShutdownHook();
    //等待当前线程终止，join，控制线程的执行顺序
    Thread.currentThread().join();
  }
  
  public void start() throws Exception
  {
    startStopLock.lock();
    try {
      //不等于NOT_STARTED，可能已经RUNNING或者停止
      if (!state.get().equals(State.NOT_STARTED)) {
        throw new ISE("Already started");
      }
      //等于NOT_STARTED，再判断一次是否有其他线程启动，若还是NOT_STARTED，置为RUNNING
      if (!state.compareAndSet(State.NOT_STARTED, State.RUNNING)) {
        throw new ISE("stop() is called concurrently with start()");
      }
      for (Map.Entry<Stage, ? extends List<Handler>> e : handlers.entrySet()) {
        currStage = e.getKey();
        log.info("Starting lifecycle [%s] stage [%s]", name, currStage.name());
        for (Handler handler : e.getValue()) {
          handler.start();
        }
      }
      log.info("Successfully started lifecycle [%s]", name);
    }
    finally {
      startStopLock.unlock();
    }
  }
}
```

# Emitter

对事件的抽象：

<img src="./picture/event.png" alt="runnable" style="zoom:50%;" />

对 `emitter` 的抽象：

<img src="./picture/emitter.png" alt="runnable" style="zoom:50%;" />

发出一个事件，方法不能被阻塞或抛异常。如果 `Emitter` 的实现收到太多的事件，内部队列已满，应该丢弃事件；如果收到无效的输入，应该打印警告日志而不能抛异常；对警告日志应该加限制，避免日志过多。

# 元数据管理

<img src="./picture/metadatamanager.png" alt="runnable" style="zoom:100%;" />

`MetadataManager` 封装所有元数据管理类：

- `CoordinatorConfigManager`：

# 节点间的服务通信

<img src="./picture/ServiceFind.png" alt="runnable" style="zoom:100%;" />



## 服务注册

<img src="./picture/DruidNodeAnnouncer.png" alt="runnable" style="zoom:50%;" />

服务注册用 `DruidNodeAnnouncer` 实现，服务启动会将信息保留在 `/druid/internal-discovery/NodeRole` 目录下，若用 `Zookeeper` 做协调者。

## 服务发现

<img src="./picture/DruidNodeDiscovery.png" alt="runnable" style="zoom:50%;" />

`DruidNodeDiscovery` 为主题，`DruidNodeDiscovery.Listener` 为观察者，用于服务发现。`ServiceDruidNodeDiscovery` 实现主题，维护 `listeners`。`FilteringUpstreamListener` 实现观察者，按服务类型过滤。

## InventoryView

<img src="./picture/InventoryView.png" alt="runnable" style="zoom:100%;" />

`InventoryView` 用于查看服务的状态，及各个服务上的段分布。

# Overlord

`Overlord` 启动过程中，会初始化 `SegmentAllocationQueue`，在 `SERVER` 状态之前。选举成为 `leader` 后，`HttpRemoteTaskRunner` 启动。

`TaskMaster` 封装了 `Overlord` 的生命周期，收集任务的统计信息。

# Segment

一个数据源的一个时间间隔可存在多个段，这些段组成一个块，由 `shardSpec` 决定分片数据。块的所有段都完成，才能开放查询。

# Ingestion

## 任务创建过程

提交任务：

1. 调用 `TaskQueue` 的 `add(Task task)` 提交任务，设置任务的 `context`，将任务插入 `TaskStorage`；
2. 更新 `TaskQueue` 的 `tasks`，和 `TaskLockbox` 的 `activeTasks`；
3. `TaskQueue` 每次轮询，遍历从 `TaskRunner` 获取的 `tasks`，如果任务就绪，调用 `TaskRunner` 的 `run(Task task)` 运行任务，否则释放该任务持有的所有锁，如果任务处于 `pending` 状态，同样尝试调用 `TaskRunner` 的 `run(Task task)` 运行任务；
4. `TaskRunner` 更新 `tasks` 和 `pendingTaskIds`，任务先置为 `pending` 状态；
5. `TaskRunner` 有线程池定期处理 `pending` 的任务，遍历 `pendingTaskIds`，按照设置的策略寻找 `worker` 调度；
6. 向 `worker` 发送请求创建任务



## TaskQueue

重要数据结构：

```java
//<taskId, Task> 保存所有状态的任务
@GuardedBy("giant")
private final LinkedHashMap<String, Task> tasks = new LinkedHashMap<>();

//<taskId, Future>
@GuardedBy("giant")
private final Map<String, ListenableFuture<TaskStatus>> taskFutures = new HashMap<>();

//已经完成等待被清除的任务，不应该再被启动
@GuardedBy("giant")
private final Set<String> recentlyCompletedTasks = new HashSet<>();
```

任务生产者和 `TaskRunner` 交互的接口，`TaskQueue` 接受由 `add` 方法添加的任务，提交给 `TaskRunner` 执行。

初始化过程：

- 同步任务表，以元数据库的结果为准更新内存；
- 同步任务锁表，获取锁失败的任务组，其所有的任务标记失败，等待清除；
- 启动工作线程，每次工作遍历内存的任务，创建就绪的任务并运行，清除状态不一致的任务；
- 启动同步线程，每次工作更新元数据库；
- 失败的任务释放锁，更新到任务锁表。

添加给定任务：

- 检查任务表是否有该任务，已存在则抛出异常；
- 设置任务的 `context`；
- 插入该任务的信息到任务表，更新状态
- 更新该任务到内存。

工作线程每次运行：

- 恢复 `TaskRunner`；
- 同步 `TaskRunner` 的状态，如果任务就绪，运行任务

## TaskRunner

### HttpRemoteTaskRunner

用内部发现和http管理 `MiddleManager` 上的任务。

## TaskLock

锁和版本紧密耦合，保证摄取数据的正确性。数据可以被覆盖，覆盖仅适用于相同的数据段和相同的 `Datasource`，被覆盖的段不会被查询。每个段都有一个大版本和一个小版本，大版本是一个时间戳，小版本是一个数字。两个版本用于确定段之间的覆盖关系。

多个任务对相同的时间块生成段，段之间会互相覆盖导致不正确的结果。为解决该问题，任务会在创建段之前获取锁。锁分为时间块锁和段锁。如果任务申请到了时间块锁，数据会写到它生成的段，其他任务无法为这个时间块生成数据段，除非有比当前段更大的大版本，它们的小版本一直是0。

段锁是任务锁定一个独立的块，多个任务可同时为同一个时间块创建段。段锁创建的段有相同的大版本和不同的小版本。使用段锁需要在任务的 `context` 里设置 `forceTimeChunkLock` 为 `false`，否则任务会自动选择合适的锁类型。

抢占锁可发生在任何时候，除非任务在发布段。同一个 `groupId` 的任务共享锁。

<img src="./picture/TaskLock.png" alt="runnable" style="zoom:100%;" />

同一个 `DataSource` 对锁冲突的检测：

- `TimeChunkLock`：时间间隔有重叠即有冲突；
- `SegmentLock`：如果是申请给新的段加锁，一定不冲突。间隔相同且分区ID相同则冲突，间隔不相同且有重叠则冲突。

对给定 `LockRequest`，检查锁是否能重用：

- 锁类型和锁粒度必须一致，否则不能重用；
- 锁类型 `REPLACE`、`APPEND`、`SHARED`，锁请求是时间间隔，锁的间隔包含请求间隔且锁的组ID等于请求的组ID才能重用；
- 锁类型 `EXCLUSIVE`，锁请求是时间间隔，锁的间隔包含请求间隔且锁的组ID等于请求的组ID才能重用；锁请求是段，锁间隔包含请求间隔且锁的组ID等于请求组ID且锁的分区ID等于请求的分区ID才能重用。

```java
boolean reusableFor(LockRequest request)
{
  if (taskLock.getType() == request.getType() && taskLock.getGranularity() == request.getGranularity()) {
    switch (taskLock.getType()) {
      case REPLACE:
      case APPEND:
      case SHARED:
        if (request instanceof TimeChunkLockRequest) {
          return taskLock.getInterval().contains(request.getInterval())
                 && taskLock.getGroupId().equals(request.getGroupId());
        }
        return false;
      case EXCLUSIVE:
        if (request instanceof TimeChunkLockRequest) {
          return taskLock.getInterval().contains(request.getInterval())
                 && taskLock.getGroupId().equals(request.getGroupId());
        } else if (request instanceof SpecificSegmentLockRequest) {
          final SegmentLock segmentLock = (SegmentLock) taskLock;
          final SpecificSegmentLockRequest specificSegmentLockRequest = (SpecificSegmentLockRequest) request;
          return segmentLock.getInterval().contains(specificSegmentLockRequest.getInterval())
                 && segmentLock.getGroupId().equals(specificSegmentLockRequest.getGroupId())
                 && specificSegmentLockRequest.getPartitionId() == segmentLock.getPartitionId();
        } else {
          throw new ISE("Unknown request type[%s]", request);
        }
        //noinspection SuspiciousIndentAfterControlStatement
      default:
        throw new ISE("Unknown lock type[%s]", taskLock.getType());
    }
  }

  return false;
}
```

### TaskLockType

追加型任务不能使用 `REPLACE` 锁，替换型任务只能用 `EXCLUSIVE` 或 `REPLACE` 锁，`REPLACE` 和 `APPENDED` 只能用于时间块锁。

```java
public enum TaskLockType
{
  //给定时间间隔内最多只能有一个独占锁，不能与有重叠间隔的锁共存
  EXCLUSIVE,
  
  //给定时间间隔内可有多个共享锁，不能与其他共享锁共存，不能与其他类型的锁共存
  SHARED,

  //给定时间间隔最多有一个替换锁，只能与间隔包含在内的追加锁共存，具有重叠间隔的其他类型锁无法兼容
  REPLACE,

  //给定时间间隔可有任意的追加锁，可与其他追加锁共存，最多与一个替换锁共存，且间隔要包含追加锁的间隔
  APPEND
}
```

### LockRequest

<img src="./picture/LockRequest.png" alt="runnable" style="zoom:100%;" />

对每种类型的锁获取锁的元数据方法进行抽象：

- `TimeChunkLockRequest`：对应时间块锁，可以被撤销；
- `SpecificSegmentLockRequest`：对应段锁，可以被撤销，有 `partitionId`；
- `LockRequestForNewSegment`：可以是段锁或时间块锁，对应要锁定新段的锁，有 `sequenceName`，会生成段ID。

### TaskLockBox

<img src="./picture/TaskLockBox.png" alt="runnable" style="zoom:80%;" />

记录活跃的任务锁定了哪些间隔和哪些段，内存和元数据库各维护一份锁的记录，内存的数据结构：

```java
//<Datasource, <startTime, <Interval, List<Tas+TaskLock>>>
private final Map<String, NavigableMap<DateTime, SortedMap<Interval, List<TaskLockPosse>>>> running = new HashMap<>();

//taskId
private final Set<String> activeTasks = new HashSet<>();
```

同步元数据库，只发生在 `TaskQueue` 启动初始化：

1. 从数据库找出活跃的任务及持有的锁，按照任务的版本升序排序；

2. 清空 `running` 和 `activeTasks`，`activeTasks` 更新为步骤1查询的结果；

3. 遍历步骤1的结果，过滤掉间隔异常的任务和锁，检查任务和锁的优先级、`dataSource`、`groupId` 是否匹配；

4. 根据锁的粒度生成 `LockRequest`，检查是否有锁可重用，若没有可重用的锁：

   1. 锁类型为 `APPEND` 或 `REPLACE`，且锁粒度为段；
   2. 锁请求与冲突的锁不能共存；
   3. 撤销所有不兼容的锁失败；

   符合以上其中一个条件，任务的组ID标记失败，等待后续杀死任务。
