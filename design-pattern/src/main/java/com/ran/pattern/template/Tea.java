package com.ran.pattern.template;

/**
 * Tea
 *
 * @author rwei
 * @since 2024/8/18 18:09
 */
public class Tea extends Beverage {
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding lemon");
    }
}
