package com.ran.pattern.template;

/**
 * Coffee
 *
 * @author rwei
 * @since 2024/8/18 17:07
 */
public class Coffee extends Beverage {
    @Override
    public void brew() {
        System.out.println("Dripping coffee through filter");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding sugar and milk");
    }
}
