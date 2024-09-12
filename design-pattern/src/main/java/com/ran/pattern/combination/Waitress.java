<<<<<<< HEAD
package com.ran.pattern.combination;

import java.util.Iterator;

/**
 * Waitress
 *
 * @author rwei
 * @since 2024/9/8 16:57
 */
public class Waitress {
    private MenuComponent menuComponent;

    public Waitress(MenuComponent menuComponent) {
        this.menuComponent = menuComponent;
    }

    public void printMenu() {
        Iterator<MenuComponent> iterator = menuComponent.createIterator();
        while (iterator.hasNext()) {
            MenuComponent component = iterator.next();
            component.print();
        }
    }
=======
package com.ran.pattern.combination;/** 
 * Waitress
 * 
 * @author rwei
 * @since 2024/9/8 16:57
 */public class Waitress {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
