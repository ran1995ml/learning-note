<<<<<<< HEAD
package com.ran.pattern.decorate;

/**
 * Whip
 *
 * @author rwei
 * @since 2024/8/5 13:12
 */
public class Whip extends CondimentDecorator {
    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.1;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }
=======
package com.ran.pattern.decorate;/** 
 * Whip
 * 
 * @author rwei
 * @since 2024/8/5 13:12
 */public class Whip {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
