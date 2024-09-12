package com.ran.pattern.singleton.hungry;

/**
 * Singleton
 *
 * @author rwei
 * @since 2024/8/11 21:48
 */
public class Singleton {
    private static final Singleton instance = new Singleton();

    private Singleton() {
    }

    public static Singleton getInstance() {
        return instance;
    }
}
