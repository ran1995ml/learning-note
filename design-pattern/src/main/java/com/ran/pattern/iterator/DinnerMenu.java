package com.ran.pattern.iterator;

/**
 * DinnerMenu
 *
 * @author rwei
 * @since 2024/8/20 13:58
 */
public class DinnerMenu implements Menu {
    private static final int MAX_ITEMS = 6;

    private int numberOfItems = 0;

    private MenuItem[] menuItems;

    public DinnerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItems("dinner", "rice", true, 2.99);
        addItems("regular", "meat", false, 2.99);
    }

    public void addItems(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.out.println("Error");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    @Override
    public Iterator<MenuItem> createIterator() {
        return new DinnerMenuIterator(menuItems);
    }
}
