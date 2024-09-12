<<<<<<< HEAD
package com.ran.pattern.singleton.lazy;

/**
 * SingletonSafe
 * 线程安全，双重锁检验
 *
 * @author rwei
 * @since 2024/8/11 22:10
 */
public class SingletonSafe {
    private volatile static SingletonSafe instance;

    private SingletonSafe() {
    }

    public static SingletonSafe getInstance() {
        if (instance == null) {
            synchronized (SingletonSafe.class) {
                if (instance == null) {
                    instance = new SingletonSafe();
                }
            }
        }
        return instance;
    }
=======
package com.ran.pattern.singleton.lazy;/** 
 * SingletonSafe
 * 
 * @author rwei
 * @since 2024/8/11 22:10
 */public class SingletonSafe {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
