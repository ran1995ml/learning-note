<<<<<<< HEAD
package com.ran.pattern.factory.abstractive;

import com.ran.pattern.factory.Pizza;

/**
 * PizzaStore
 *
 * @author rwei
 * @since 2024/8/9 10:47
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.box();
        return pizza;
    }

    public abstract Pizza createPizza(String type);
=======
package com.ran.pattern.factory.abstractive;/** 
 * PizzaStore
 * 
 * @author rwei
 * @since 2024/8/9 10:47
 */public class PizzaStore {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
