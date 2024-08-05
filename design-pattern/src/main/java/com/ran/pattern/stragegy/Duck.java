package com.ran.pattern.stragegy;

/**
 * Duck
 *
 * @author rwei
 * @since 2024/7/31 10:02
 */
public abstract class Duck {
    Fly fly;

    Quack quack;

    public Duck() {
    }

    public abstract void display();

    public void performFly() {
        fly.fly();
    }

    public void performQuack() {
        quack.quack();
    }

    public void setFly(Fly fly) {
        this.fly = fly;
    }

    public void setQuack(Quack quack) {
        this.quack = quack;
    }
}
