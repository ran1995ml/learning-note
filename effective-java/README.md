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
