package com.ran.pattern.stragegy;

/**
 * MallardDuck
 *
 * @author rwei
 * @since 2024/7/31 10:11
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        fly = new FlyWithWings();
        quack = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("mallard");
    }
}
