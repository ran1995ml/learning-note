<<<<<<< HEAD
package com.ran.pattern.combination;

import java.util.Iterator;

/**
 * MenuComponent
 *
 * @author rwei
 * @since 2024/9/8 16:37
 */
public abstract class MenuComponent {
    public abstract Iterator<MenuComponent> createIterator();

    public void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    public MenuComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public void print() {
        throw new UnsupportedOperationException();
    }
=======
package com.ran.pattern.combination;/** 
 * MenuComponent
 * 
 * @author rwei
 * @since 2024/9/8 16:37
 */public class MenuComponent {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
