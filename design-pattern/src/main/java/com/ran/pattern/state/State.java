package com.ran.pattern.state;

/**
 * State
 *
 * @author rwei
 * @since 2024/9/8 21:52
 */
public interface State {
    void insertQuarter();

    void ejectQuarter();

    void turnCrank();

    void dispense();
}
