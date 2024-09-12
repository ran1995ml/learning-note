<<<<<<< HEAD
package com.ran.pattern.decorate;

/**
 * Main
 *
 * @author rwei
 * @since 2024/8/5 13:14
 */
public class Main {
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + beverage.cost());
        beverage = new Mocha(beverage);
        beverage = new Whip(beverage);
        System.out.println(beverage.getDescription() + beverage.cost());
    }
=======
package com.ran.pattern.decorate;/** 
 * Main
 * 
 * @author rwei
 * @since 2024/8/5 13:14
 */public class Main {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
