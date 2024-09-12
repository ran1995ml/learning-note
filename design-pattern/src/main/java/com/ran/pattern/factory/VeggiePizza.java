package com.ran.pattern.factory;

import com.ran.pattern.factory.abstractive.IngredientFactory;

/**
 * VeggiePizza
 *
 * @author rwei
 * @since 2024/8/6 22:36
 */
public class VeggiePizza extends Pizza {
    IngredientFactory ingredientFactory;

    public VeggiePizza(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public VeggiePizza() {
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
