package com.ran.pattern.factory.simple;

import com.ran.pattern.factory.Pizza;

/**
 * PizzaStore
 *
 * @author rwei
 * @since 2024/8/6 22:38
 */
public class PizzaStore {
    SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        Pizza pizza = factory.createPizza(type);
        pizza.prepare();
        pizza.box();
        return pizza;
    }
}
