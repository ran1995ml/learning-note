<<<<<<< HEAD
package com.ran.pattern.state;

/**
 * NoQuarterState
 *
 * @author rwei
 * @since 2024/9/8 21:57
 */
public class NoQuarterState implements State {
    private Gumball gumball;

    public NoQuarterState(Gumball gumball) {
        this.gumball = gumball;
    }

    @Override
    public void insertQuarter() {
        System.out.println("inserted quarter");
        gumball.setState(gumball.getHasQuarterState());
    }

    @Override
    public void ejectQuarter() {
        System.out.println("no quarter ejected");
    }

    @Override
    public void turnCrank() {
        System.out.println("turned, but no quarter");
    }

    @Override
    public void dispense() {
        System.out.println("need to pay first");
    }
=======
package com.ran.pattern.state;/** 
 * NoQuarterState
 * 
 * @author rwei
 * @since 2024/9/8 21:57
 */public class NoQuarterState {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
