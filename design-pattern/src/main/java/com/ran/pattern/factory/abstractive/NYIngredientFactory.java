<<<<<<< HEAD
package com.ran.pattern.factory.abstractive;

import com.ran.pattern.factory.Cheese;
import com.ran.pattern.factory.NYCheese;
import com.ran.pattern.factory.NYSauce;
import com.ran.pattern.factory.Sauce;

/**
 * NYIngredientFactory
 *
 * @author rwei
 * @since 2024/8/9 10:36
 */
public class NYIngredientFactory implements IngredientFactory {
    @Override
    public Cheese createCheese() {
        return new NYCheese();
    }

    @Override
    public Sauce createSauce() {
        return new NYSauce();
    }
=======
package com.ran.pattern.factory.abstractive;/** 
 * NYIngredientFactory
 * 
 * @author rwei
 * @since 2024/8/9 10:36
 */public class NYIngredientFactory {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
