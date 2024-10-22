package com.ran.pattern.bridge;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/16 20:45
 */
public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedDrawAPI());
        Shape greenCircle = new Circle(100, 100, 10, new GreenDrawAPI());
        redCircle.draw();
        greenCircle.draw();
    }
}
