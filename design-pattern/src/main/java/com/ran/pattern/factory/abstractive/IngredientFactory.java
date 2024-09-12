package com.ran.pattern.factory.abstractive;

import com.ran.pattern.factory.Cheese;
import com.ran.pattern.factory.Sauce;

/**
 * IngredientFactory
 *
 * @author rwei
 * @since 2024/8/9 10:33
 */
public interface IngredientFactory {
    Cheese createCheese();

    Sauce createSauce();
}
