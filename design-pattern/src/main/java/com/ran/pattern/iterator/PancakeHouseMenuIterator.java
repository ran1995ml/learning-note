<<<<<<< HEAD
package com.ran.pattern.iterator;

import java.util.List;

/**
 * PancakeHouseMenuIterator
 *
 * @author rwei
 * @since 2024/8/20 21:44
 */
public class PancakeHouseMenuIterator implements Iterator<MenuItem> {
    private final List<MenuItem> menuItems;

    private int position = 0;

    public PancakeHouseMenuIterator(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return position < menuItems.size();
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = menuItems.get(position);
        position++;
        return menuItem;
    }
=======
package com.ran.pattern.iterator;/** 
 * PancakeHouseMenuIterator
 * 
 * @author rwei
 * @since 2024/8/20 21:44
 */public class PancakeHouseMenuIterator {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
