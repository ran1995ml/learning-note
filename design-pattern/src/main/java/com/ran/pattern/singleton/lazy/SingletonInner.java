<<<<<<< HEAD
package com.ran.pattern.singleton.lazy;

/**
 * SingletonInner
 * 获取实例时内部类才会装载
 *
 * @author rwei
 * @since 2024/8/11 22:12
 */
public class SingletonInner {
    private SingletonInner() {
    }

    public static SingletonInner getInstance() {
        return Singleton.instance;
    }

    private static class Singleton {
        private static SingletonInner instance = new SingletonInner();
    }
=======
package com.ran.pattern.singleton.lazy;/** 
 * SingletonInner
 * 
 * @author rwei
 * @since 2024/8/11 22:12
 */public class SingletonInner {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
