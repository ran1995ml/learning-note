package com.ran.pattern.factory.factory;

import com.ran.pattern.factory.Pizza;

/**
 * Main
 *
 * @author rwei
 * @since 2024/8/7 10:51
 */
public class Main {
    public static void main(String[] args) {
        PizzaStore pizzaStore = new NYPizzaStore();
        Pizza pizza = pizzaStore.orderPizza("cheese");
    }
}
