package com.ran.pattern.prototype;

/**
 * Circle
 *
 * @author rwei
 * @since 2024/9/26 11:36
 */
public class Circle extends Shape {
    public Circle() {
        type = "circle";
    }

    @Override
    public void draw() {
        System.out.println("draw circle");
    }
}
