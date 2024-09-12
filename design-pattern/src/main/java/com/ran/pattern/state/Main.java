package com.ran.pattern.state;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/8 22:44
 */
public class Main {
    public static void main(String[] args) {
        Gumball gumball = new Gumball(1);

        gumball.insertQuarter();
        gumball.ejectQuarter();
        System.out.println();

        gumball.insertQuarter();
        gumball.turnCrank();
        System.out.println();

        gumball.insertQuarter();
        gumball.turnCrank();
        System.out.println();
    }
}
