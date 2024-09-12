package com.ran.pattern.decorate;

/**
 * Espresso
 *
 * @author rwei
 * @since 2024/8/5 11:25
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
