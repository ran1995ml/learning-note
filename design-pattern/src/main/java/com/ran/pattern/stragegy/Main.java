package com.ran.pattern.stragegy;

/**
 * Main
 *
 * @author rwei
 * @since 2024/7/31 10:18
 */
public class Main {
    public static void main(String[] args) {
        Duck duck = new MallardDuck();
        duck.performQuack();
        duck.performFly();
        duck.setFly(new FlyNoWay());
        duck.performFly();
    }
}
