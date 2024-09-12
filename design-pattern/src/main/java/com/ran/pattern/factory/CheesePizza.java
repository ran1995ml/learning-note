<<<<<<< HEAD
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
=======
package com.ran.pattern.factory;/** 
 * CheesePizza
 * 
 * @author rwei
 * @since 2024/8/6 22:36
 */public class CheesePizza {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
