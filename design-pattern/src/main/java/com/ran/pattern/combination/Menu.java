<<<<<<< HEAD
package com.ran.pattern.combination;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Menu
 *
 * @author rwei
 * @since 2024/9/8 16:50
 */
public class Menu extends MenuComponent {
    private List<MenuComponent> menuItems = new ArrayList<>();

    private String name;

    private String description;

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new CompositeIterator(menuItems.iterator());
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menuItems.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menuItems.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int index) {
        return menuItems.get(index);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void print() {
        System.out.printf("name: %s, description: %s%n", name, description);
        for (MenuComponent menuItem : menuItems) {
            menuItem.print();
        }
    }
=======
package com.ran.pattern.combination;/** 
 * Menu
 * 
 * @author rwei
 * @since 2024/9/8 16:50
 */public class Menu {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
