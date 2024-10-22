package com.ran.pattern.bridge;

/**
 * Circle
 *
 * @author rwei
 * @since 2024/9/16 20:23
 */
public class Circle extends Shape {
    private final int x;

    private final int y;

    private final int radius;

    protected Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
