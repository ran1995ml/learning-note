package com.ran.pattern.state;

/**
 * SoldState
 *
 * @author rwei
 * @since 2024/9/8 22:05
 */
public class SoldState implements State {
    private Gumball gumball;

    public SoldState(Gumball gumball) {
        this.gumball = gumball;
    }

    @Override
    public void insertQuarter() {
        System.out.println("already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("sorry, the crank is turned");
    }

    @Override
    public void turnCrank() {
        System.out.println("can't turn twice");
    }

    @Override
    public void dispense() {
        gumball.releaseBall();
        if (gumball.getCount() > 0) {
            gumball.setState(gumball.getNoQuarterState());
        } else {
            gumball.setState(gumball.getSoldOutState());
        }
    }
}
