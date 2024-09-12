<<<<<<< HEAD
package com.ran.pattern.iterator;

/**
 * Waitress
 *
 * @author rwei
 * @since 2024/8/20 21:50
 */
public class Waitress {
    private Menu pancakeHouseMenu;

    private Menu dinnerMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinnerMenu) {
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinnerMenu = dinnerMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> pancakeHouseMenuIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinnerMenuIterator = dinnerMenu.createIterator();
        System.out.println("dinner: ");
        print(dinnerMenuIterator);
        System.out.println("breakfast: ");
        print(pancakeHouseMenuIterator);
    }

    private void print(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = iterator.next();
            System.out.println(menuItem);
        }
    }
=======
package com.ran.pattern.iterator;/** 
 * Waitress
 * 
 * @author rwei
 * @since 2024/8/20 21:50
 */public class Waitress {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
