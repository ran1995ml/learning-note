# 多线程优势

线程允许在同一个进程中同时存在多个程序控制流，会共享进程范围内的资源。线程是轻量级进程，大多数操作系统都以线程为基本调度单位。

程序中只包含一种类型的任务，比包含多种不同类型的任务更易于编写。为每种类型的任务分配专门的线程，可形成一种串行执行的假象。使用线程，可将复杂且异步的工作流分解为一组简单且同步的工作流，每个工作流在单独的线程中执行，在特定的同步位置交互。

# 锁

只有当类中仅包含自己的状态时，线程安全类才是有意义的。线程的安全性，当多个线程访问某个类，不管运行时环境采用何种调度方式，且不需要做额外的同步或协同，这个类始终表现出正确的行为，那么这个类是线程安全的。无状态的对象一定是线程安全的。

当某个计算的正确性取决于多个线程的交替执行时序时，会发生竞态条件。要确保对同一个状态的操作是原子的。使用线程安全的对象管理类的状态，如用 `AtomicLong` 代替 `Long`。

如果有多个状态，需要在单个原子操作中更新所有相关的状态变量。锁机制可支持原子性，`Java` 中提供了同步代码块

```java
synchronized(lock) {
//访问或修改由锁保护的共享状态
}
```

每个对象都可用作锁，静态 `synchronized` 方法以 `Class` 对象作为锁。线程进入代码块前要获得锁，退出代码块自动释放锁。锁是一种互斥体，当线程获取的锁已被其他线程占有，必须等待或阻塞。

重入，意味着当线程获取自己已经占有的锁，该请求会成功。重入意味着获取锁的粒度是线程，而不是调用。重入的实现方法是，为每个锁关联一个获取计数值和一个所有者线程，计数值为0时，该锁被认为没有任何线程持有。重入可避免一些死锁情况的发生，比如子类改写父类中的同步方法，并调用父类方法。

```java
public class Widget {
		public sychronized void do() {}
}

public class SubWidget extends Widget {
  	public sychronized void do() {
      	super.do();
    }
}
```

锁能使保护的代码路径串行访问，因此可通过锁构造一些协议以实现对共享状态的独占访问，始终遵循协议，即可保证状态的一致性。如果复合操作的执行过程中持有一把锁，会使复合操作成为原子操作。当使用锁协调对某个变量的访问，需要使用同一把锁。对象的内置锁和状态没有关联，只是为了免去显式创建锁对象。

锁会带来性能问题，当执行时间较长的计算时，不要持有锁。

# 对象共享

`synchronized` 能实现原子性，还能保证内存可见性，当一个线程修改对象状态后，其他线程能看到发生的状态变化。

`volatile` 声明变量，确保将变量的更新操作通知到其他线程，编译器运行时不会将该变量上的操作与其他内存操作重排，不会被缓存，读取到的总是最新的值，不会执行加锁操作，是更轻量级的同步机制。读取 `volatile` 变量，相当于进入同步，写入 `volatile` 变量，相当于退出同步。

`volatile` 变量通常用作标志，不能保证操作的原子性。使用条件：

- 对变量的写入不依赖变量的当前值；
- 该变量不会与其他状态变量一起纳入不变性条件中；
- 访问变量时不需要加锁。

发布对象，使对象能在当前作用域之外的代码中使用。如果在对象构造完成之前就发布该对象，会破坏线程安全性。若需要在构造函数中创建线程，不要立即启动，最好通过 `start` 或 `initialize` 启动。若要在构造方法中注册事件监听器或启动线程，可用私有构造方法和一个公共工厂方法，避免不正确的构造过程。这也体现了封装的好处，能分析程序的正确性。

当访问共享的可变数据，需要使用同步，避免使用同步的方式是不使用同步。线程封闭是实现线程安全最简单的方式之一：

- `Ad-hoc` 封闭：维护线程封闭性完全由程序实现，如设计成单线程系统。`volatile` 变量存在特殊的线程封闭，若只有单个线程对变量修改，即可安全地执行读取、修改、写入操作，将修改封闭到单个线程防止发生竞态条件。
- 栈封闭：只能通过局部变量访问对象，自然地将对象封闭在执行线程中。
- `ThreadLocal`：使线程中的某个值与保存值的对象关联起来，用于防止对可变的单实例变量或全部变量共享。如每个线程保存属于自己的数据库连接。

不变性也是满足同步需求的一种方法，不可变对象一定是线程安全的。对象创建后状态不可变、所有域都是 `final`、创建期间没有逸出，这样的对象才是不可变的。如果不需要更高的可见性，应将所有域声明为 `final`。`volatile` 变量的引用指向一个不可变对象，可确保线程安全。

安全发布对象，对象的引用和状态必须同时对其他线程可见：

- 在静态初始化函数中初始化一个对象引用；
- 保存对象引用到 `volatile` 的域或 `AtomicReference` 对象中；
- 保存对象引用到某个正确构造对象的 `final` 域中；
- 保存对象引用到一个由锁保护的域中，如将对象放入线程安全的容器。

静态初始化器在类初始化阶段执行，虚拟机内部存在同步机制，因此通过这种方式初始化的任何对象都可以被安全发布。