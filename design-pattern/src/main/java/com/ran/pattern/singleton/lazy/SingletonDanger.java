<<<<<<< HEAD
package com.ran.pattern.singleton.lazy;

/**
 * SingletonDanger
 * 非线程安全
 *
 * @author rwei
 * @since 2024/8/11 22:07
 */
public class SingletonDanger {
    private static SingletonDanger instance;

    private SingletonDanger() {
    }

    public static SingletonDanger getInstance() {
        if (instance == null) {
            instance = new SingletonDanger();
        }
        return instance;
    }
=======
package com.ran.pattern.singleton.lazy;/** 
 * SingletonDanger
 * 
 * @author rwei
 * @since 2024/8/11 22:07
 */public class SingletonDanger {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
