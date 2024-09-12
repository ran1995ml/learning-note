package com.ran.pattern.factory.factory;

import com.ran.pattern.factory.NYCheesePizza;
import com.ran.pattern.factory.NYVeggiePizza;
import com.ran.pattern.factory.Pizza;

/**
 * NYPizzaStore
 *
 * @author rwei
 * @since 2024/8/7 10:36
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new NYCheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new NYVeggiePizza();
        }
        return pizza;
    }
}
