<<<<<<< HEAD
package com.ran.pattern.factory.simple;

import com.ran.pattern.factory.CheesePizza;
import com.ran.pattern.factory.Pizza;
import com.ran.pattern.factory.VeggiePizza;

/**
 * SimplePizzaFactory
 *
 * @author rwei
 * @since 2024/8/6 22:34
 */
public class SimplePizzaFactory {
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        if (type.equals("cheese")) {
            pizza = new CheesePizza();
        } else if (type.equals("veggie")) {
            pizza = new VeggiePizza();
        }
        return pizza;
    }
=======
package com.ran.pattern.factory.simple;/** 
 * SimplePizzaFactory
 * 
 * @author rwei
 * @since 2024/8/6 22:34
 */public class SimplePizzaFactory {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
