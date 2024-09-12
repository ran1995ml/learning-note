package com.ran.pattern.adapter;

/**
 * MallardDuck
 *
 * @author rwei
 * @since 2024/8/15 14:25
 */
public class MallardDuck implements Duck {
    @Override
    public void quack() {
        System.out.println("mallard duck quack");
    }

    @Override
    public void fly() {
        System.out.println("mallard duck fly");
    }
}
