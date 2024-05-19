# Effective Java

## 1. 静态工厂方法替代构造方法

一个类提供一个公共静态工厂方法，返回类实例。

```java
public static Boolean valueOf(boolean b) {
		return b ? Boolean.TRUE : Boolean.FALSE;
}
```

第一个优点，和构造方法相比，有名字，更易于阅读；第二个优点，不需要每次调用都创建一个新对象。经常请求等价对象可极大提高性能，特别在创建对象非常昂贵的情况下。第三个优点，可以返回子类对象，使API更紧凑。第四个优点，返回对象的类可根据输入参数的不同而不同。第五个优点，编写包含该方法的类，返回的对象的类不需要存在。

服务提供者框架是提供者实现服务的系统。服务提供者框架有三个基本组：服务接口，表示实现；提供者注册API，提供者用来注册实现；服务访问API，客户端使用该API获取服务实例，允许客户指定选择实现的标准，是灵活的静态工厂。服务提供者框架可选的第四个组件是服务提供者接口，描述一个生成服务接口实例的工厂对象。没有服务提供者，需要对实现进行反射实例化。

比如 `JDBC`，`Connection` 是服务接口的一部分，`DriverManager.registerDriver` 提供注册API，`DriverManager.getConnection` 是服务访问API，`Driver` 是服务提供者接口。

服务提供者框架模式可有很多变种，如服务访问API可向客户端返回比提供者更丰富的服务接口，即桥接模式。依赖注入框架可看作是强大的服务提供者。

第一个缺点，没有公共或受保护构造方法的类不能被子类化。第二个缺点，不好找。

## 2. 构造方法参数过多使用builder模式

对象参数过多，且只有少数几个可选属性，怎么办？可使用可伸缩的构造方法，只提供所需参数的构造方法，但参数数量增加会很快失控。还可以使用 `JavaBean` 模式，调用无参构造方法创建对象，再调用 `setter` 设置可选参数，但该模式构造方法在多次调用中分割，构造过程中可能处于不一致的状态。

为解决上述问题，可使用 `builder` 模式。客户端调用构造方法或静态工厂获得一个 `builder` 对象，然后设置 `builder` 对象的 `setter` 方法设置可选参数，最后调用一个无参的 `build` 方法生成对象，该对象通常是不可变的。

```java
public class NutritionFacts {
    private final int size;
    private final int fat;
    private final int sodium;

    public static class Builder {
        private final int size;
        private final int fat;
        private int sodium = 0;

        public Builder(int size, int fat) {
            this.size = size;
            this.fat = fat;
        }

        public Builder sodium(int val) {
            this.sodium = val;
            return this;
        }

        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }

    private NutritionFacts(Builder builder) {
        size = builder.size;
        fat = builder.fat;
        sodium = builder.sodium;
    }
}
```

调用：

```java
NutritionFacts nutritionFacts = new NutritionFacts.Builder(1, 1).sodium(1).build();
```

可添加参数有效性检查，检查失败抛出 `IllegalArgumentException`。

该模式很适合类层次结构，抽象类有抽象的 `builder`，具体类有具体的 `builder`。

```java
public abstract class Pizza {
    public enum Topping {PEPPER, HAM}

    final Set<Topping> toppings;

  	//T类型和当前Builder类型保持一致，这样在具体子类中可通过Builder的方法返回自身类型的实例，实现链式调用
    abstract static class Builder<T extends Builder<T>> {
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);

        public T addTopping(Topping topping) {
            toppings.add(topping);
            return self();
        }

        abstract Pizza build();

      	//返回自身对象
        protected abstract T self();
    }

    Pizza(Builder<?> builder) {
        toppings = builder.toppings.clone();  // item 50
    }
}
```

`Pizza.Builder` 是一个带有递归类型参数的泛型类型，结合抽象的 `self` 方法，允许方法链在子类中正常工作，不需要强制转换，这种方法称为模拟自我类型。

子类实现：

```java
public class NyPizza extends Pizza {
    public enum Size {SMALL, LARGE}

    private final Size size;

    public static class Builder extends Pizza.Builder<Builder> {
        private final Size size;

        public Builder(Size size) {
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }

    private NyPizza(Builder builder) {
        super(builder);
        this.size = builder.size;
    }
}
```

每个子类 `builder` 的 `build` 方法声明为返回正确的子类，这种一个子类的方法被声明为返回在超类中声明的返回类型的子类型，称为协变返回类型。客户端使用这些 `builder`，可以不用强制转换。

优点：`builder` 可以有多个可变参数，可将传递给多个调用的参数聚合到单个属性中，单个 `builder` 可重复使用构建多个对象。缺点：创建对象必须创建 `builder`，相比构造方法模式更冗长，有足够多的参数才值得使用。

## 3. 用私有构造方法或枚举实现Singleton

单例是一个仅实例化一次的类，通常表示无状态对象。第一种方法是私有化构造方法，用 `final` 修饰的公共成员提供对实例的唯一访问。

```java
public class Elvis1 implements Serializable {
    public static final transient Elvis1 instance = new Elvis1();

    private Elvis1() {}
}
```

但是特权客户端可通过反射调用私有构造方法，若要防御此攻击，需要修改构造函数，在请求创建第二个实例时抛出异常。

第二种方法，公共成员是一个静态工厂方法。优点是可灵活改变，无论类是否为单例都不必改变API；可以编写一个泛型单例工厂；可以使用 `supplier`。

```java
public class Elvis2 implements Serializable {
    private static final transient Elvis2 instance = new Elvis2();

    private Elvis2() {}

    public static Elvis2 getInstance() {
        return instance;
    }
  
  	private Object readSolve() {
        return instance;
    }
}
```

如果实例被反序列化，就会创建一个新的实例，需要提供 `readResolve` 方法保护单例。

第三种方法，声明单一元素的枚举类。

```java
public enum Elvis3 {
    INSTANCE;
}
```

该方法更简洁，提供了免费的序列化机制，是实现单例的最佳方式，但如果单例必须继承其他类，就不能使用该方法。

## 4. 用私有构造方法执行非实例化

工具类可实现对静态方法分组，不需要实例化。但编译器为其提供一个公共无参的构造方法。想避免实例化，可通过包含一个私有构造方法实现类的非实例化，并且可以阻止子类化。

## 5. 用依赖注入取代hardwiring resources

创建新实例将资源传递到构造方法

```java
public class SpellChecker {
    private final Lexicon dictionary;

    public SpellChecker(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
}
```

该模式的变体是将资源工厂传递给构造方法

```java
Mosaic create(Supplier<? extends Tile> tileFactory) {}
```

若包含的依赖过大，还是建议使用依赖注入框架。总之，如果类依赖其他资源，不要直接创建资源，将资源或工厂传递给构造方法。

## 6. 避免创建不必要的对象

如果对象是不可变的，应当重用不应该新建。以下做法很不可取

```java
String s = new String("abc");
```

使用静态工厂，可避免创建不需要的对象。如果要重复使用一个昂贵的对象，可以缓存起来。

```java
public class RomanNumerals {
    private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})");

    static boolean isRomanNumeral(String s) {
        return ROMAN.matcher(s).matches();
    }
}
```

如果该方法从未被调用，可以延迟初始化。

要优先使用基本类型而不是装箱的基本类型，要注意无意识的自动装箱。

除非池中的对象非常昂贵，如数据库连接，否则不要维护对象池避免对象创建。

## 7. 消除过期的对象引用

过期引用永远不会解除，引用的对象永远不会被回收，导致内存泄漏。要解决这类问题，一旦对象引用过期，设置为 `null`。另一个好处是，如果随后被错误引用，会抛出 `NullPointerException`，可尽快发现错误。

消除过期引用的最好方法是让包含引用的变量超出范围。当一个类自己管理内存时，需要警惕内存泄漏。

## 8. 避免使用Finalizer和Cleaner

`Finalizer` 的缺点是不能保证及时执行，当一个对象变得无法访问时，到 `Finalizer` 开始运行的时间是任意长的。`Finalizer` 的运行优先级低于其他应用程序线程，对象被回收的速度低于进入队列的速度。

最好的方法是强制让类实现 `Closeable` 接口，实现 `close()` 方法代替 `Finalizer`。

## 9. try-with-resources替代try-finally

`try-finally` 可以关闭资源，即使程序抛出异常

```java
public void copy(String src, String dst) throws IOException {
    InputStream in = Files.newInputStream(Paths.get(src));
    try {
        OutputStream out = Files.newOutputStream(Paths.get(dst));
        try {
            byte[] buf = new byte[100];
            int n = 0;
            while ((n = in.read(buf)) >= 0) {
                out.write(buf, 0, n);
            }
        } finally {
            out.close();
        }
    } finally {
        in.close();
    }
}
```

不建议用两层 `try-finally`，第二层的异常会冲掉第一层的异常，导致很难排查问题。

`try-with-resources` 可解决，使用该构造的资源必须实现 `AutoCloseable` 接口。

```java
public void copy(String src, String dst) throws IOException {
    try (InputStream in = new FileInputStream(src);
         OutputStream out = new FileOutputStream(dst)) {
        byte[] buf = new byte[64];
        int n = 0;
        while ((n = in.read(buf)) >= 0) {
            out.write(buf, 0, n);
        }
    }
}
```

## 10. 重写equals遵守通用约定

如果一个类包含逻辑相等的概念，且父类还没有重写过 `equals`，此时才需要重写 `equals` 方法。重写 `equals` 方法，必须遵守通用约定：

- 自反性，`x.equals(x)` 返回 `true`；
- 对称性，`x.equals(y)` 返回 `true`，`y.equals(x)` 必须返回 `true`；
- 传递性，`x.equals(y)` 返回 `true`，`y.equals(z)` 返回 `true`，则 `x.equals(z)` 必须返回 `true`；
- 一致性，比较过程中若没有信息修改，`x.equals(y)` 的多次调用结果必须相同。

编写高质量 `equals` ：

- 用 `==` 检查参数是否为该对象引用，起到性能优化的作用；
- 用 `instanceof` 检查参数类型是否正确；
- 参数转换为正确类型；
- 检查每个重要属性是否匹配。

## 11. 重写equals也要重写hashcode

`hashCode` 的通用约定：

- 一个应用程序执行过程，对象没有被修改，重复调用 `hashCode`，必须始终返回相同的值，从一个应用程序到另一个应用程序执行返回到值可以是不一致的。
- `equals` 比较结果相等，`hashCode` 必须产生相同的结果；
- `equals` 比较结果不等，`hashCode` 产生结果可以相等。

## 12. 始终重写toString方法

## 13. 谨慎使用clone方法

## 14. 考虑使用Comparable接口

一个类表明它的实例有一个自然顺序，可实现 `Comparable` 接口。若需要有多种比较方法，可创建 `Comparator`。

## 15. 使类和成员的可访问性最小化

如果一个包级私有顶级类或接口只被一个类使用，可以考虑这个类作为使用它的唯一类的私有静态嵌套类。这将它的可访问性从包级的所有类减少到使用它的一个类。公共类是包的 `API` 的一部分，包私有的顶级类已经是这个包实现的一部分了。

如果一个方法重写一个超类方法，那么它在子类的访问级别就不能低于父类中的访问级别。

## 16. 公共类中使用访问方法而不是公共属性

公共类不应该暴露可变属性。

## 17. 最小化可变性

不可变类比可变类更容易设计，不容易出错，创建一个不可变类：

- 不要提供修改对象状态的方法；
- 确保该类不能被继承；
- 把所有属性设置为 `final`；
- 把所有属性设置为 `private`；
- 确保对任何可变组件的互斥访问。

方法名称是介词而不是动词，强调了方法不会改变对象的值的事实。函数式方法，方法返回将操作数应用于函数的结果，而不修改它们，返回一个新的实例，具有不变性。

不可变对象是线程安全的，可以被自由共享，鼓励客户端尽可能重用现有实例，缺点是对于每个不同的值都需要一个单独的对象。

不可变类实现 `Serializable` 接口，且包含一个活一个引用可变对象的属性，必须提供显式的 `readObject` 或 `readResolve` ，否则攻击者可能会创建一个可变的类的实例。

不要为每个属性编写一个 `get` 方法后再编写一个 `set` 方法，除非有充足的理由使类成为可变类，否则类应该是不可变的。如果一个类不能设计为不可变类，也要尽可能限制它的可变性。

除非有充足的理由不这样做，否则应该把每个属性声明为私有 `final`。构造方法应该创建完全初始化的对象，并建立所有的可变性。

## 18. 组合优于继承

继承打破了封装，父类的实现变化，子类可能会被破坏，这样子类必须与父类一起更新。

组合，给新类增加一个私有属性，该属性是现有类的实例引用，新类的方法调用现有类的方法返回结果，成为转发。这样产生的类不依赖于现有类的实现细节，类似装饰器模式。

使用继承前，确定父类的 `API` 是否有缺陷，能否接受这些缺陷传递下去。继承是强大的，但违反了封装，只有在子类真的是父类的子类型的情况下，继承才是合适的。

## 19. 使用继承设计，必须加文档说明

## 20. 接口优于抽象类

接口是定义混合类型的理想选择，缺点是不允许包含实例属性或非公共静态成员，不能将默认方法添加到不受控制的接口中。但是，通过提供一个抽象的骨架实现类与接口一起使用，可将接口和抽象类的优点结合起来。接口定义了类型，提供一些默认方法，骨架实现类在原始接口方法的顶层实现剩余的非原始接口方法，即模板方法模式。

骨架类提供抽象类的所有实现的帮助，但不会加强抽象类作为类型定义时的严格约束。如果一个类不能继承骨架的实现，它可以直接实现接口。

## 21. 为后代设计接口

增加新的方法到现有的接口是充满风险的。即使是使用默认方法，也应该避免。

## 22. 接口仅用来定义类型

类实现接口时，接口作为一种类型引用类的实例，只能用来定义类型。

## 23. 优先使用类层次而不是标签类

避免使用 `switch` 标签类，容易出错。子类型化，为定义一个能表示多种风格对象的单一数据类型提供了更好的选择。首先定义一个包含抽象方法的抽象类，该标签类的行为取决于标签值。

## 24. 优先考虑静态成员类

嵌套类有：静态成员类，非静态成员类，匿名类和局部类。后三种都被称为内部类。静态成员类可看作一个普通类，可以访问所有宿主类的成员，如果被声明为 `private`，则只能在宿主类中访问。一个常见用途时作为公共帮助类，仅在与外部类一起使用才有用。

如果成员类不需要访问宿主实例，声明为 `static`。若忽略了该修饰符，每个实例都会有一个隐藏的外部引用给宿主实例，即使宿主类被垃圾回收，该类可能仍然在内存中。

如果一个嵌套的类需要在一个方法之外可见，使用一个成员类。如果一个成员类的每个实例都需要一个对其宿主实例的引用，使其成为非静态类，否则为静态类。如果该类属于一个方法内部，需要从一个地方创建实例，用匿名类，否则用局部类。

## 25. 将源文件限制为单个顶级类

一个文件中只定义一个顶级类。

## 26. 不要使用原始类型

原始类型的存在主要是为了和泛型之前的代码兼容。不确定类型，需要用通配符 `Set<?>`。通配符是安全的，原始类型不是。

使用原始类型的例外：

- `List.class` 是合法的，`List<String>.class` 不是合法的；
- 泛型类型在运行时被删除，使用 `instanceof` 需要用原始类型。

## 27. 消除非检查警告

如果不能消除编译警告，但可以证明引发警告的代码是类型安全的，可以用 `@SuppressWarning("unchecked")` 抑制警告，每当使用时，添加注释说明。

## 28. 列表优于数组

数组是协变的，泛型是不变的。数组被具体化了，在运行时知道并强制执行它们的元素类型。泛型通过擦除来实现，只在编译时执行类型约束，在运行时擦除它们的元素类型，这样做时为了泛型类型与不使用泛型的遗留代码自由互操作。数组提供运行时类型的安全性，不提供编译时类型的安全性，列表相反。

## 29. 优先考虑泛型

## 30. 优先使用泛型方法

```java
public static <E extends Comparable<E>> E max(Collection<E> c);
```

`<E extends Comparable <E>>`，可理解为任何可以与自己比较的类型。

## 31. 用限定通配符增加API的灵活性

如果一个参数化类型代表一个 `T` 生产者，使用 `<? extends T>`；如果它代表 `T` 消费者，使用 `<? super T>`。

如果类型参数在方法声明中只出现一次，将其替换为通配符。如果是一个无限制的类型参数，替换为无限制的通配符；如果是一个限定类型参数，替换为限定通配符。

## 32. 合理结合泛型和可变参数

可变参数和泛型不能很好地交互，数组具有与泛型不同的类型规则。如果使用泛型可变参数编写方法，先确保该方法是类型安全的，然后使用 `@SafeVarargs` 对其标注。

## 33. 优先考虑类型安全的异构容器

类型令牌 `Class<T>` 是泛型的。泛型 `API` 限制了每个容器的固定数量的类型参数，可以将类型参数放在键上解决此限制，使用 `Class` 对象作为此类型安全异构容器的键。

## 34. 用枚举类型替代整型常量

枚举是其合法值由一组固定的常量组成的一种类型。枚举之前是使用静态常量，该方法没有提供类型安全的方式，编译时不会出现警告，如果相关值发生更改，必须重新编译客户端。

`Java` 枚举是完整的类，通过公共静态常量属性为每个枚举常量导出一个实例的类，没有可访问的构造方法，枚举类型实际是 `final`。客户端不能创建枚举类型，是单例的。枚举提供了编译时类型的安全性。

如果一组常量在编译时已知，此时可以使用枚举。

## 35. 用实例属性替代序数

不要从枚举的 `ordinal` 方法得出与它相关的值，将其保存在实例属性中。

## 36. 用EnumSet替代位属性

`EnumSet` 表示从单个枚举类型中提取的值集合，每个 `EnumSet` 都表示为一个位矢量。所以天然适合存储位属性。

```java
EnumSet.of(Style.BOLD, Style.ITALIC);
```

## 37. 用EnumMap替代序数索引

`EnumMap` 用作从枚举到值的映射。

## 38. 使用接口模拟可扩展的枚举

枚举的可扩展性很差，但可以用接口来模拟。通过创建新的枚举实现接口来做扩展。

## 39. 注解优于命名模式

## 40. 始终使用Override注解

## 41. 用标记接口定义类型

标记接口，不包含方法声明，只是指定一个类实现了具有某些属性的接口。

如果想定义一个没有任何关联的新方法的类型，一个标记接口是一个可行的方法。

如果不想定义一个类型，不要使用接口；如果想定义一个类型，一定要使用接口。

## 42. lambda表达式优于匿名类

如果函数很短，优先用 `lambda` 表达式。

## 43. 方法引用优于lambda表达式

方法引用，用 `Class::Method` 进一步简化 `lambda` 表达式。

## 44. 优先使用标准的函数式接口

如果用 `lambda` 表达式设计，优先使用 `java.util.function` 提供的标准函数式接口。常用的接口：

- `Operator`：方法的结果和参数类型相同；
- `Predicate`：方法接受一个参数返回一个布尔值；
- `Function`：方法的参数和返回类型不同；
- `Supplier`：不接受参数和返回值的方法；
- `Consumer`：方法接受一个参数不返回任何东西

## 45. 谨慎使用Stream

过度使用 `Stream` 会使程序难于阅读维护。流无法修改局部变量，不能抛出已声明的异常。统一转换元素，过滤元素才适合使用流。

## 46. 优先使用Stream中无副作用的函数

单 `forEach` 不要用，只是用于报告结果。

## 47.优先用Collection而不是Stream作为返回类型

## 48.谨慎使用流并行

## 49. 检查参数有效性

## 50. 必要时进行防御性拷贝

## 51. 仔细设计方法签名

仔细选择方法名称；不要过分提供方便的方法，经常使用才考虑快捷方式；避免过长的参数列表，目标是4个或更少。参数类型优先选择接口而不是类；布尔类型，优先使用两个元素的枚举值。

## 52. 慎用重载

## 53. 慎用可变参数

## 54. 返回空的数组或集合，不要返回null
