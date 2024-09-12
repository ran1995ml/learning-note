package com.ran.pattern.iterator;

/**
 * DinnerMenuIterator
 *
 * @author rwei
 * @since 2024/8/20 14:15
 */
public class DinnerMenuIterator implements Iterator<MenuItem> {
    private final MenuItem[] menuItems;

    private int position = 0;

    public DinnerMenuIterator(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }

    @Override
    public boolean hasNext() {
        return position < menuItems.length && menuItems[position] != null;
    }

    @Override
    public MenuItem next() {
        MenuItem menuItem = menuItems[position];
        position++;
        return menuItem;
    }
}
