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
}
