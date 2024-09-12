<<<<<<< HEAD
package com.ran.pattern.decorate;

/**
 * Mocha
 *
 * @author rwei
 * @since 2024/8/5 11:27
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }
=======
package com.ran.pattern.decorate;/** 
 * Mocha
 * 
 * @author rwei
 * @since 2024/8/5 11:27
 */public class Mocha {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
