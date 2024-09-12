package com.ran.pattern.factory;

/**
 * Pizza
 *
 * @author rwei
 * @since 2024/8/6 22:34
 */
public abstract class Pizza {
    Sauce sauce;

    Cheese cheese;

    public abstract void prepare();

    public abstract void box();
}
