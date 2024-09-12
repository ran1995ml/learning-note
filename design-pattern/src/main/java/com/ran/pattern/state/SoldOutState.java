package com.ran.pattern.state;

/**
 * SoldOutState
 *
 * @author rwei
 * @since 2024/9/8 22:09
 */
public class SoldOutState implements State {
    private Gumball gumball;

    public SoldOutState(Gumball gumball) {
        this.gumball = gumball;
    }

    @Override
    public void insertQuarter() {
        System.out.println("sold out");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("sold out");
    }

    @Override
    public void turnCrank() {
        System.out.println("sold out");
    }

    @Override
    public void dispense() {
        System.out.println("sold out");
    }
}
