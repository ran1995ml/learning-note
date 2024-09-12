package com.ran.pattern.factory;

import com.ran.pattern.factory.abstractive.IngredientFactory;

/**
 * CheesePizza
 *
 * @author rwei
 * @since 2024/8/6 22:36
 */
public class CheesePizza extends Pizza {
    IngredientFactory ingredientFactory;

    public CheesePizza(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public CheesePizza() {
    }

    @Override
    public void prepare() {
        cheese = ingredientFactory.createCheese();
        sauce = ingredientFactory.createSauce();
    }

    @Override
    public void box() {

    }
}
