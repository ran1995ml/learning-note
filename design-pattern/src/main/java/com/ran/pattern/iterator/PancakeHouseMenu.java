<<<<<<< HEAD
package com.ran.pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * PancakeHouseMenu
 *
 * @author rwei
 * @since 2024/8/20 13:53
 */
public class PancakeHouseMenu implements Menu {
    List<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItems("breakfast", "egg", true, 2.99);
        addItems("regular", "sausage", false, 2.99);
    }

    public void addItems(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new PancakeHouseMenuIterator(menuItems);
    }
=======
package com.ran.pattern.iterator;/** 
 * PancakeHouseMenu
 * 
 * @author rwei
 * @since 2024/8/20 13:53
 */public class PancakeHouseMenu {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
