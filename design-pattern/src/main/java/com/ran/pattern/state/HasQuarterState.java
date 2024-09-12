package com.ran.pattern.state;

/**
 * HasQuarterState
 *
 * @author rwei
 * @since 2024/9/8 22:05
 */
public class HasQuarterState implements State {
    private Gumball gumball;

    public HasQuarterState(Gumball gumball) {
        this.gumball = gumball;
    }

    @Override
    public void insertQuarter() {
        System.out.println("can't insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        System.out.println("quarter returned");
        gumball.setState(gumball.getNoQuarterState());
    }

    @Override
    public void turnCrank() {
        System.out.println("turned");
        gumball.setState(gumball.getSoldState());
    }

    @Override
    public void dispense() {
        System.out.println("no gumball dispensed");
    }
}
