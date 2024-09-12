package com.ran.pattern.singleton.lazy;

/**
 * SingletonEnum
 * 反射不能保证实例唯一，但枚举可以
 *
 * @author rwei
 * @since 2024/8/11 22:15
 */
public enum SingletonEnum {
    instance;

    SingletonEnum() {
    }

    public static void main(String[] args) {
        SingletonEnum instance1 = SingletonEnum.instance;
    }
}
