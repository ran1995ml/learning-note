package com.ran.pattern.iterator;

/**
 * Main
 *
 * @author rwei
 * @since 2024/8/20 21:57
 */
public class Main {
    public static void main(String[] args) {
        Menu pancakeHouseMenu = new PancakeHouseMenu();
        Menu dinnerMenu = new DinnerMenu();
        Waitress waitress = new Waitress(pancakeHouseMenu, dinnerMenu);
        waitress.printMenu();
    }
}
