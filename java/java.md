# JavaSE

特点：面向对象；平台无关，编译成字节码运行在 `JVM`；自动管理内存。

`JVM` 是跨平台的关键，将字节码翻译成机器码执行，字节码在不同平台相同，机器码可能不同。

`JRE`，`Java` 运行时环境，包含 `JVM` 和一些类库；`JDK`，开发工具包，包含 `JRE` 和一些工具，如 `javac`、`javadoc`。

编译型语言：程序运行前被编译成机器码或字节码，生成可执行文件运行。解释型语言：程序执行时逐行执行源代码，不生成独立的可执行文件，由解释器动态解释并执行。

装箱和拆箱是将基本数据类型和对应包装类间转换的过程。弊端：循环中自动装箱会创建多余的对象。`Java` 集合只能存储对象，不能存基本数据类型，此时需要用到包装类。基本数据类型的包装类内有一个静态缓存池，会优先复用缓存中现有的对象，不会每次都生成新的实例对象。

面向对象特性：封装，将对象属性和行为结合在一起，对外隐藏对象内部细节，增强安全性，使对象更独立。继承，子类自动共享父类属性和方法，建立类之间的层次关系，使结构更清晰。多态，同一个接口，可使用不同实例执行，增强扩展性。

重载：同一个类可有多个同名方法，具有不同的参数列表，编译器根据调用时的参数类型决定调用方法。重写：子类重写父类方法。

抽象类描述类的共同特性和行为，可有成员变量、构造方法，抽象类本身不能被实例化，可以有构造器用于子类实例化调用。接口定义行为规范，可多实现，只能有常量、抽象方法和默认方法。

非静态内部类依赖于外部类实例，静态内部类可独立于外部类创建。非静态内部类可访问外部类的实例变量和方法，静态内部类只能访问外部类的静态成员。非静态内部类不能定义静态成员，静态内部类可定义静态成员。非静态内部类在外部类实例化后才能实例化，静态内部类可独立实例化。非静态内部类可直接访问外部类私有成员，静态内部类需要通过实例化外部类访问。

编译器生成字节码时为非静态内部类维护一个指向外部类实例的引用，因此非静态内部类可直接访问外部类方法。

深拷贝创建一个新的对象。浅拷贝创建的对象引用类型字段仍然和源对象指向相同的对象。

泛型的目的是在编译时提供更强的类型检查，允许类、接口和方法在定义时使用类型参数，使用时指定为具体类型。适用于多种数据类型执行相同的代码，泛型类型在使用时指定，不需要强制类型转换。

创建对象的方式：`new` 创建；反射创建；`clone` 创建；反序列化。

反射：能动态获取调用任意类和对象的属性和方法。应用场景：加载数据库驱动，动态根据情况加载驱动类。`Spring` 的 `IOC`，将配置文件加载如类型，解析得到实体类的相关信息，用反射获得某个类的 `Class` 实例，动态配置实例属性。这里的动态是编译时提供好所需的类，运行时根据配置文件选择要装载的类，不需要在编译时就确定依赖关系。

注解：继承了 `Annotation` 的特殊接口，具体实现类是 `Java` 运行时生成的动态代理类。注解作用域：可应用的程序元素，包括类、方法、字段。

异常基类 `Throwable`：`Error`，运行时环境错误不捕获，如 `OutOfMemoryError`、`StackOverflowError`。`Exception`，程序本身可处理的异常，非运行时异常，在编译时必须被捕获或抛出，如 `ClassNotFoundException`；运行时异常，如 `NullPointException`，运行时产生的错误。未检查异常，继承自 `RuntimeException`，编译器不强制要求异常处理，不需要在方法中 `throws`。

`==` 比较的是变量本身的值或对象在内存中的地址。`equals` 可重写，比较对象的值。

`String` 是 `final` 类不可变。`StringBuffer` 用于解决大量字符串拼接产生很多中间对象，线程安全。`StringBuilder` 非线程安全。

对象从一个 `JVM` 传到另一个 `JVM`：序列化和反序列化，将对象序列化为字节流发送给另一个 `JVM`，再反序列化恢复。消息传递，使用消息队列传递，需要指定序列化方式。`RPC`，远程调用，屏蔽了网络传输细节。数据库或缓存，读 `Redis` 或 `MySQL` 再反序列化。

主流序列化框架：`FastJson` 和 `Protobuf`。追求性能用 `Protobuf`，压缩效果好，编码解码效率高。确保类实现 `Serializable` 接口，包括其所有成员变量。

`NIO`，同步非阻塞 `IO`，同步指线程轮询 `IO` 事件是否就绪，非阻塞指线程等待 `IO` 可做其他任务。同步的核心是 `Selector`，代替了线程本身轮询 `IO` 事件，避免了阻塞。非阻塞核心是 `Channel` 和 `Buffer`，`IO` 事件就绪写到缓冲区保证 `IO` 成功。`NIO` 由专门的线程处理所有 `IO` 事件并分发。事件到来再出发操作，线程间通过 `wait` 和 `notify` 通信，减少线程切换。

`NIO` 基于 `Channel` 和 `Buffer` 操作，数据总是从 `Channel` 读到 `Buffer`，或从 `Buffer` 写入 `Channel`。`Selector` 监听通道事件，如连接建立，数据到达。

`Netty` 基于 `NIO` 的 `Selector`，用一个线程负责 `Selector` 的轮询，数据就绪用事件分发器 `Dispatcher` 分发数据到 `Handler`。事件分发器类型：`Reactor`，用同步 `IO`，适用耗时短的场景，实现简单；`Proactor`，用异步 `IO`，实现逻辑复杂，适合图片或视频流场景。

数组固定长度，创建后长度无法改变，集合是动态长度。数据可包含基本数据类型，集合只能包含对象。数据可直接访问元素，集合需要通过方法或迭代器。

# Collection

`PriorityQueue`，优先队列，可按比较器排序，用于实现堆。

`ArrayList`，非线程安全，底层用数组实现，扩容时会创建更大的数据，将原数组复制过去，支持随机访问。初始容量是10，每次扩容为原来的1.5倍。

`LinkedList`，双向链表，支持快速插入删除，随机访问速度较慢。

`HashSet`，通过 `HashMap` 实现，`HashMap` 的 `Key` 即 `HashSet` 存储的元素，所有 `Key` 的值相同。只保证元素唯一，不保证有序。保证元素的自然顺序用 `TreeSet`，保证元素的添加顺序用 `LinkedHashSet`。

`Map` 存储键值对，`Key` 无序但要唯一。`HashMap`，数组加链表实现，链表用于解决哈希冲突，链表长度大于8时会转换为红黑树减少搜索事件。`ConcurrentHashMap`，线程安全，数组加红黑树实现，老版本用段锁，新版本用 `CAS`。`LinkedHashMap`，哈希表加双向链表，实现 `LRU`。`TreeMap`，基于红黑树实现有序的 `Map`，添加元素时按照比较规则插入到合适的位置。哈希表的长度设为2的幂次方，是为了提高为 `Key` 查找位置的效率，若除数是2的幂次方，`hash%length==hash%(length - 1)`，与运算可提高效率。

`ConcurrentHashMap` 用分段锁实现线程安全，只锁一部分，后在每个元素上加锁，减少冲突概率。若 `Key` 对应数组为空，用 `CAS` 操作，不为空则加锁。

`ConcurrentSkipListMap`，基于跳表实现。

`CopyOnWriteArrayList`，所有写操作通过底层数组复制实现，在新数组上操作，最后用新数组替换旧数组，读操作则直接返回结果。

`ConcurrentLinkQueue`， `CAS` 操作实现。`BlockingQueue` 简化线程间数据共享，实现生产者-消费者模式。`ArrayBlockingQueue`，数组实现，没有分离读写锁，同一时间只能有一个线程操作。`LinkedBlockingQueue`，链表实现，读和写分别用独立的锁。

迭代器遍历集合，用于需要删除元素的情况。

解决哈希冲突：用链表存储；用另一个哈希函数再次计算直到找到空槽；哈希冲突过多，动态扩容重新分配键值对减少冲突。

`HashMap` 添加键值，根据 `Key` 计算哈希码获取在数组中的位置，若为空，创建新的 `Entry` 存储，若有其他 `Key`，添加到链表，链表长度超过8，转换为红黑树。若键值对数量与数组长度比值大于阈值，进行扩容，创建一个两倍大小的新数组，重新计算哈希码分配到新数据。

`HashMap` 未初始化，用 `null` 调用 `get` 会抛 `NPE`，已经完成初始化，用 `null` 为 `Key` 是允许的，哈希值为0。因为 `String` 不可变，其作为 `Key` 更稳定。平衡树追求的是完全平衡，任何节点左右子树高度差不能超过1，开销较大影响性能，所以用红黑树追求弱平衡。

`HashMap` 初始数组长度为16，当容量超过75%，触发扩容，每次扩容为以前的2倍。

`ConcurrentHashMap` 对数组进行分段，每个段包含多个 `HashEntry` 链表结构。容器为空用 `CAS` 操作，不为空加锁。对不同的段分别加锁，不同段的操作互不影响，提高并发性能。

# Concurrency

`JMM`，`Java` 内存模型，定义多线程环境共享变量的可见性和指令重排规则，确保并发行为是可预测的。原子性：一个操作是不可中断的，一旦开始不会被其他线程干扰。可见性：一个线程修改了共享变量，其他线程能立即知道修改。有序性：为提高执行效率采取指令重排，前提要保证串行语义的一致性。

`Happen-Before` 原则保证有序性：同一线程内写在前面的操作先执行；解锁发生在加锁前；线程开始必然先于他的每一个动作。

`JMM` 主内存：所有线程共享的内存区域。工作内存：每个线程独立的内存区域，从主内存读取共享变量到工作内存，执行完操作将结果写回主内存。

保证数据一致性：锁控制并发访问，避免并发操作导致数据不一致。版本控制，用乐观锁更新数据时记录版本，避免同时对同一数据修改，保证数据一致性。

线程创建方式：继承 `Thread` 类，重写 `run()` 后调用 `start()` 启动。实现 `Runnable` 接口，重写 `run()` 方法传给 `Thread` 类构造器，调用 `start()` 执行。实现 `Callable` 接口，重写 `call()` 方法可有返回值，包装进一个 `FutureTask` 类，调用 `get()` 获取结果。线程池创建。

停止线程方法：调用 `interrupt()` 给线程发送中断信号，在 `run()` 中判断 `interrupted()` 状态，若是中断状态抛出异常处理。先把线程 `sleep`，调用 `interrupt()` 会将阻塞状态的线程中断。

当一个线程被其他线程调用 `Thread.interrupt()` 中断时，若该线程在执行低级别的可中断方法，如 `sleep()`、`join()`、`wait()`，会解除阻塞抛出 `InterruptedException`，否则仅标记线程的中断状态，被中断的线程通过轮询决定操作。

线程的状态：`NEW`，创建未启动。`RUNNABLE`，就绪等待被调度。`BLOCKED`，等待锁陷入阻塞。`WAITING`，等待另一个线程执行特定操作。`TERMINATED`，完成执行终止。

`sleep()` 暂停线程一段时间，释放 `CPU` 资源但不会释放锁。`wait()` 暂停线程释放锁，等待其他线程唤醒。`join()` 等待目标线程完成，不释放锁。

`synchronized` 同步代码块或方法，确保同一时刻只有一个线程可访问。

`ReentrantLock` 提供了高级功能，如公平锁、锁超时、多个条件变量、响应中断。

`volatile` 保证变量的可见性，线程会将变量值从主存缓存到私有内存或 `CPU` 寄存器，不能及时更新到主存。`volatile` 保证变量的更新会直接写入主存，每次直接从主存读取。防止指令重排，对 `volatile` 变量写操作前的指令不能排到其后面。实现了 `JMM` 的可见性和有序性，但不能保证原子性。

原子类 `AtomicInteger`，提供原子操作不需要额外同步。

`ThreadLocal`，为线程提供独立的变量副本，避免线程间资源竞争。`Thread` 类有 `ThreadLocalMap` 成员变量，`key` 是 `ThreadLocal` 的引用，`value` 是保存的值。用完要显式 `remove()` 清理，`key` 是弱引用，`value` 是强引用。

`synchronized` 锁升级：无锁。偏向锁，偏向于第一个获取锁的线程，没有其他线程竞争不需要同步。轻量级锁，用 `CAS` 操作。自旋锁，等待一段时间，假设锁的持有时间都很短。重量级锁，被操作系统挂起，需要从用户态转换到内核态，让出 `CPU`。虚拟机在编译阶段，检测到共享数据不可能存在竞争，执行锁消除。锁粗化，尽可能减少锁的作用范围。

`AQS`，`AbstractQueuedSynchronizer`，用于构建锁、同步器的框架。核心思想：若被请求的共享资源空闲，将请求线程设置为有效工作线程，共享资源设为锁定状态，若共享资源被占用，将暂时获取不到锁的线程加入队列。队列的实现是单向链表，每个请求线程封装成一个节点实现锁分配。使用一个 `volatile` 变量表示同步状态，队列完成资源获取排队，用 `CAS` 修改状态。

实现乐观锁：`CAS`，版本号控制。

`CAS` 的缺点：`ABA`问题，中间被修改的过程不能被感知，通过添加标志解决。长时间不成功 `CPU` 开销很大。只能保证一个共享变量原子操作，可用 `AtomicReference` 或 `synchronized` 实现。  

非公平锁的吞吐量比公平锁大，因为不按 `FIFO` 获取，通过 `CAS` 获取锁，成功拥有锁，失败去等待队列，避免线程阻塞等待。

线程池，减少频繁创建和销毁线程。提交一个任务，若核心线程数未满，创建线程，满了加入等待队列，队列满了增加线程直到达到最大线程数，达到最大线程数执行拒绝策略。线程数小于核心线程数，即使任务处于空闲也不会被销毁。最大线程数，线程池最多可容纳大线程数。线程池数量大于核心线程数，若某个线程空闲超过设定的空闲时间会被销毁。

线程池拒绝策略：用线程池调用者所在线程执行被拒绝任务。抛出拒绝异常停止。丢弃最老的任务执行。

参数设置：`CPU` 密集型，核心线程数 = `CPU` 核数 + 1。`IO` 密集型，核心线程数 = `CPU` 核数 * 2。

`Exectors` 提供的快捷线程池：`ScheduledThreadPool`，周期性执行任务。`SingleThreadExecutor`，用唯一线程执行任务，适合所有任务都需要按被提交的顺序执行的场景。

向线程池提交任务，会得到一个 `Future` 对象，可用 `cancel()` 方法取消执行的任务。

# Spring

`IOC` 容器，通过控制反转实现对象的创建和对象间的依赖管理关系，定义好 `Bean` 及依赖关系，`Spring` 容器负责创建组装，降低对象间的耦合度。利用反射动态加载类、创建对象实例调用方法。用工厂模式管理对象的创建，容器作为工厂。`ApplicationContext` 作为工厂。

`AOP`，面向切面编程，定义横切关注点，如权限控制，独立于业务逻辑的代码，把与业务无关，却为业务模块所共同调用的逻辑封装，减少重复代码，降低模块耦合度。实现依赖于动态代理技术，在运行时动态生成代理对象，而不是编译时。为所有服务层方法添加记录日志功能，定义一个切面，用动态代理创建目标类的代理对象，调用方法前后，该代理对象会执行切面定义的逻辑，在运行时通过反射构建。

`AOP` 的概念：连接点，程序执行过程中的一个点，如方法调用。通知，定义的一个切面中的横切逻辑，有 `around`，`before` 和 `after`。通知通常作为拦截器。切点，匹配连接点，筛选切面中的连接点。

控制反转：程序执行流程由框架控制。依赖注入：将依赖的对象在外部创建好传入使用。依赖倒置：抽象不依赖具体实现。

设置 `IOC`：`Bean` 的生命周期管理，考虑用工厂模式和单例模式。依赖注入，考虑用反射。配置文件加载，考虑用注解、配置类。

循环依赖：两个类中的属性相互依赖对象，形成依赖闭环。`Spring` 只解决通过 `setter` 注入单例的循环依赖。创建 `Bean` 的过程通过三级缓存来缓存正在创建的 `Bean` 及已完成创建的 `Bean`。

三级缓存都是 `Map`。一级缓存，存储已完全初始化的 `Bean`。二级缓存，存储已实例化还未完全初始化的 `Bean`。三级缓存，`Bean` 创建过程若被其他 `Bean` 依赖，先创建一个空引用暴露出去，解决循环依赖。

用到的设计模式：模板方法，`jdbcTemplate` 等。单例、工厂，`Bean` 的窗口。单例，`AOP` 实现

常用注解：`@Autowired`，自动装配 `Bean`。`@Component`，实例化为 `Bean`。`@Bean`，标记方法作为 `Bean` 工厂方法，将该方法返回值作为 `Bean`。

`@Transactional` 失效的情况：抛出异常。事务方法内部调用的方法没有用 `@Transactional`。`@Transactional` 标注在非公有方法。不能用 `this` 调用，事务是通过代理对象控制的。

注解注入 `Bean`，容器启动时扫描类路径，查找带有特定注解的类，注册到容器内。实例化 `Bean` 时，检查字段上是否有注解，根据信息注入。

`MVC`：视图，为用户提供界面直接交互。模型，代表一个存取数据到对象，分为数据承载 `Bean` 和业务处理 `Bean`。控制器，将用户请求转发给相应模型处理，使模型和视图分离。

`MyBatis` 将 `SQL` 写在 `XML`，解除 `SQL` 与代码的耦合，便于统一管理。使用：在配置文件中配置数据源、`Mapper` 文件位置；创建实体类与数据库表对应；编写 `SQL` 映射文件；编写 `DAO` 接口；编写具体的 `SQL` 查询语句，调用查询方法。执行时处理 `#{}` 替换成 `?`，执行时再对占位符赋值，提高安全性。

`MyBatisPlus` 继承 `BaseMapper` 接口，提供了一些内置的快捷方法，提供了代码生成器，封装了很多常用的方法，内置了分页插件。

`MyBatis` 使用了建造者模式、工厂模式、单例模式、代理模式、模版方法模式。

`JDBC` 连接过程：加载数据库驱动，建立连接创建 `Statement` 对象，执行 `SQL` ，处理查询结果关闭连接。 

# JVM

内存结构：方法区存在本地内存，`java` 命令为 `JVM` 申请内存后的同时，通过系统调用 `mmap()` 申请元空间，存储类、常量、静态变量。虚拟机栈：随线程创建而创建，存储栈帧，存放了局部变量、方法出口等信息。本地方法栈：用于 `Native` 方法调用。程序计数器：当前线程执行字节码的行号指示器。堆：创建对象数组，通过 `GC` 回收。直接内存：避免在堆内堆外复制数据，`DirectByteBuffer` 对象作为引用操作，用 `native` 库分配堆外内存。

堆分为新生代和老年代。新生代分为一块 `Eden` 和两块 `Survivor`，新对象分配到 `Eden`，`Eden` 满了触发 `Minor GC`，活下来的对象放入其中一个 `Survivor`。经过多次 `Minor GC` 仍存活的对象移动到老年代，老年代对象生命周期较长，`Major GC` 的频率较低，老年代的空间比较大。大对象需要连续内存空间，直接分配老年代，避免 `Minor GC` 产生内存碎片。

方法区：类信息，类的结构信息、访问修饰符、父类及接口。常量池，类和接口的常量，字面量和符号引用及运行时常量池。静态变量。

引用的分类：强引用，永远不会被回收。软引用：内存溢出前才会回收。弱引用：下次垃圾回收时会被回收，不管内存是否足够。虚引用：相当于没有引用，不能访问对象，被回收时加入到队列执行 `hook`。

弱引用不会阻止对象被垃圾回收，在内存压力大时被回收，避免内存泄漏。主要用于实现缓存，避免内存泄漏，通过弱引用获取的对象可能为 `null`，需要做下判断。

内存泄漏：程序运行过程不再使用的对象仍被使用，无法被垃圾回收，导致可用内存减少。静态集合未清理，未取消对事件的监听，未停止的线程可能持有对象引用。内存溢出：虚拟机申请内存无法找到足够的内存，除了程序计数器，都有可能出现内存溢出。

内存溢出分析：大量使用静态变量。大量未关闭的资源，始终记得在 `finally` 关闭资源，且不能发生异常。

每个 `Thread` 维护一个 `ThreadLocalMap`，`key` 是 `ThreadLocal` 实例是弱引用，没有强引用引用它就会被回收，`value` 是需要存储的对象。`value` 存储的对象可能比较大，如数据库连接，所以声明为强引用保证确定性。

创建对象的过程：先检查对象对应的类是否被加载，若没有，先执行类加载。类加载过程可确定对象大小，完毕后在堆中划分出一块内存。对所有成员变量赋初值，保证正常使用。设置对象头，包括哈希码、`GC` 年龄、锁状态、类的元数据信息。执行构造方法。

对象的生命周期：创建，分配对象内存空间。使用，被引用执行相应操作。销毁，不再被引用被垃圾回收。

类加载过程：通过包名+类名从二进制字节流获取类的信息，生成 `Class` 对象作为对该类的访问入口，存储在方法区。验证字节流内容合法，为静态字段分配内存分配初始值。将常量池的符号引用替换为直接引用。执行 `static` 语句块。以下情况类被回收：该类的所有实例被回收。该类的类加载器被回收。该类的 `Class` 对象没有被引用。

类加载器：启动类加载器，加载最顶层的类；扩展类加载器，加载扩展目录的类，父加载器是启动类加载器；系统类加载器，加载用户类路径上的类，父加载器是扩展类加载器。类加载器间形成双亲委派模型，当一个类加载器收到类加载请求，先把请求委派给父加载器，所有的类加载请求都要经过启动类加载器，父类加载器无法完成，子类加载器才尝试加载。

双亲委派模型保证了类的唯一性，避免不同类加载器重复加载，防止用户自定义类覆盖核心类。不同层次的类加载器服务不同的类，便于维护扩展。提高加载效率，安全性，会缓存，避免扫描所有类，避免重复加载。如果只用一个核心类加载器，会缺乏灵活性。

垃圾回收触发：内存不足；手动请求；对象数量或内存使用达到阈值。

判断对象被回收：引用计数器法，为每个对象分配一个引用计数器，每当有一处引用加1，引用失效减1，为0表示可以回收，无法解决两个对象相互引用。可达性分析法：从一组 `GC Roots`（栈中在引用的对象、静态属性引用的对象）出发，追溯引用的对象，若一个对象到 `GC Roots` 每任何引用链，则不可达可被回收。

垃圾回收算法：标记-清除，标记出要回收的对象统一回收，缺点是产生大量内存碎片，导致申请大块内存再次 `GC`。复制：内存分成两块，每次只使用一块，将存活的对象复制到另一块，整块清理掉，解决内存碎片，但每次只使用一半导致利用率低，存活对象较少的情况效率高。标记-整理：标记后将存活的对象移动到一端，清理剩余对象，用于老年代存活率较高的情况。分代收集，将对象分为新生代和老年代，每经历一次 `GC` 次数年龄加1，超过一定值进入老年代。

垃圾收集器：`Serial`，单线程，新生代用复制，老年代用标记-整理，响应速度优先，用于单核 `CPU` 客户端模式。`ParNew`，多线程，新生代用复制，响应速度优先，用于多核 `CPU` 客户端模式。`Parallel Scavenge(Old)`，新生代用复制，老年代用标记-整理，吞吐量优先，用于后台计算、交互少的场景。

`CMS` 以获取最短回收停顿时间为目标，响应速度优先，用于老年代，新生代和 `ParNew` 配合使用。暂停用户线程标记与 `GC Roots` 直连的对象，开启用户线程，记录可达对象。修正并发标记过程中发生的变动，清除判断死亡的对象。

`minorGC`：只针对年轻代回收，有 `Eden` 和两个 `Survivor`，`Eden` 空间不足时触发，转移到空闲的 `Survisor` 或老年代。`MajorGC`：主要针对老年代回收，老年代空间不足或检测到老年代晋升速度过快触发，回收需要更长时间。`FullGC`：对整个堆回收，`MinorGC` 后老年代空间不足，需要暂停所有工作线程。

# IDEA提效
`Code Completion` 取消 `Match case` 不区分大小写提示
`Auto Import` 增加 `Optimize imports` 和 `Add unambigous imports`
`File Encoding` 和 `Console` 字符集统一 `utf-8`
设置 `File and Code Templates`，`Includes` 添加 `File Header`
```
/**
 * ${NAME}
 * @author rwei
 * @since ${DATE} - ${TIME}
 */
*/
```
`Compile` 设置 `Build project automatically` 和 `Compile independent modules in parallel`
`Editor Tabs` 取消设置 `Show tabs in one row` 

`Debug`：
`Step Over` 单步执行；
`Step Into` 进入方法；
`Step Out` 跳出方法；
`Resume Program` 跳到下一个断点；
`Force Step Into` 进入 `SDK` 的方法；
`Return to Cursor` 跳转到光标位置；
`Show Execution Point` 光标跳转到当前执行的代码行；






