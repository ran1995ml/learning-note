package com.ran.pattern.factory.abstractive;

import com.ran.pattern.factory.CheesePizza;
import com.ran.pattern.factory.Pizza;
import com.ran.pattern.factory.VeggiePizza;

/**
 * NYPizzaStore
 *
 * @author rwei
 * @since 2024/8/9 10:47
 */
public class NYPizzaStore extends PizzaStore {
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        IngredientFactory ingredientFactory = new NYIngredientFactory();

        if (type.equals("cheese")) {
            pizza = new CheesePizza(ingredientFactory);
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza(ingredientFactory);
        }

        return pizza;
    }
}
