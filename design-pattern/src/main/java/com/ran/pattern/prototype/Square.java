package com.ran.pattern.prototype;

/**
 * Square
 *
 * @author rwei
 * @since 2024/9/26 11:38
 */
public class Square extends Shape {
    public Square() {
        type = "square";
    }

    @Override
    public void draw() {
        System.out.println("draw square");
    }
}
