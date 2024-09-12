package com.ran.pattern.decorate;

/**
 * HouseBlend
 *
 * @author rwei
 * @since 2024/8/5 11:26
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend";
    }

    @Override
    public double cost() {
        return 0.98;
    }
}
