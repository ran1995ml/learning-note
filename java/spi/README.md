# SPI机制

全称`Service Provider Interfaces`，服务提供接口，解耦服务提供与服务使用。

`JDK` 中 `SPI` 的具体实现：`ServiceLoader`

定义服务接口约定：

```java
public interface MyServiceProvider {
    String getName();
}
```

服务实现：

```java
public class MyServiceProviderImpl1 implements MyServiceProvider {
    public String getName() {
        return "my service 1";
    }
}
```

注册服务，以便使用方发现服务，在 `/META-INF/services/` 目录下创建文件 `com.ran.spi.MyServiceProvider`

```
com.ran.spi.impl.MyServiceProviderImpl1
com.ran.spi.impl.MyServiceProviderImpl2
```

## 参考文献

https://juejin.cn/post/6844903891746684941
