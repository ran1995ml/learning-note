package com.ran.pattern.decorate;

/**
 * Beverage
 *
 * @author rwei
 * @since 2024/8/3 22:08
 */
public abstract class Beverage {
    String description = "unknown";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
