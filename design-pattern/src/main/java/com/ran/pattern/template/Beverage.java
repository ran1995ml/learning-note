package com.ran.pattern.template;

/**
 * Beverage
 *
 * @author rwei
 * @since 2024/8/18 18:28
 */
public abstract class Beverage {
    public final void prepare() {
        boilWater();
        brew();
        addCondiments();
        pourInCup();
    }

    public abstract void brew();

    public abstract void addCondiments();

    public void boilWater() {
        System.out.println("Boiling water");
    }

    public void pourInCup() {
        System.out.println("Pouring into cup");
    }
}
