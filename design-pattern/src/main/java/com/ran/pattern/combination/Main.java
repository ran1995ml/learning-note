package com.ran.pattern.combination;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/8 16:58
 */
public class Main {
    public static void main(String[] args) {
        MenuComponent breakfast = new Menu("breakfast menu", "breakfast");
        MenuComponent dinner = new Menu("dinner menu", "dinner");
        MenuComponent menu = new Menu("total menu", "total");

        menu.add(breakfast);
        menu.add(dinner);
        menu.add(new MenuItem("coffe", "coffe", 10.01));

        dinner.add(new MenuItem("pizza", "pizza", 3.89));
        dinner.add(new MenuItem("soup", "soup", 0.73));

        breakfast.add(new MenuItem("cake", "cake", 1.21));

        Waitress waitress = new Waitress(menu);
        waitress.printMenu();
    }
}
