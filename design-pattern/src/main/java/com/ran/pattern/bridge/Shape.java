package com.ran.pattern.bridge;

/**
 * Shape
 *
 * @author rwei
 * @since 2024/9/16 20:22
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
