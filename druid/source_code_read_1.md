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

<img src="./picture/runnable.png" alt="runnable" style="zoom:50%;" />

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
