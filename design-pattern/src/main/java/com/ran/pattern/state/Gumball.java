<<<<<<< HEAD
package com.ran.pattern.state;

/**
 * Gumball
 *
 * @author rwei
 * @since 2024/9/8 21:58
 */
public class Gumball {
    private State soldOutState;

    private State noQuarterState;

    private State hasQuarterState;

    private State soldState;

    private State state;

    private int count;

    public Gumball(int count) {
        this.soldOutState = new SoldOutState(this);
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldState = new SoldState(this);
        this.count = count;
        if (count > 0) {
            this.state = this.noQuarterState;
        }
    }

    public void insertQuarter() {
        this.state.insertQuarter();
    }

    public void ejectQuarter() {
        this.state.ejectQuarter();
    }

    public void turnCrank() {
        this.state.turnCrank();
        this.state.dispense();
    }

    public void setState(State state) {
        this.state = state;
    }

    public void releaseBall() {
        System.out.println("release a ball");
        if (count > 0) {
            count--;
        }
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
=======
package com.ran.pattern.state;/** 
 * Gumball
 * 
 * @author rwei
 * @since 2024/9/8 21:58
 */public class Gumball {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
