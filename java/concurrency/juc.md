# JUC

## CountDownLatch

调用 `await` 会阻塞，每调用一次 `countDown()` 减一，减为零会唤醒阻塞的线程。

```java
final CountDownLatch workerViewInitialized = new CountDownLatch(1);
this.nodeDiscoveryListener = new DruidNodeDiscovery.Listener()
{
  @Override
  public void nodeViewInitialized()
  {
    //CountDownLatch.countDown() does nothing when count has already reached 0.
    workerViewInitialized.countDown();
  }
};

while (!workerViewInitialized.await(30, TimeUnit.SECONDS)) {
  if (System.currentTimeMillis() - workerDiscoveryStartTime > TimeUnit.MINUTES.toMillis(5)) {
    throw new ISE("Couldn't discover workers.");
  } else {
    log.info("Waiting for worker discovery...");
  }
}
```

