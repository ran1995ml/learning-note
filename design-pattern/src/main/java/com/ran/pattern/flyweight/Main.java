package com.ran.pattern.flyweight;

/**
 * Main
 * 蝇量模式，减少相似对象
 * @author rwei
 * @since 2024/9/18 11:20
 */
public class Main {
    private static final String[] colors = {"red", "blue", "white", "black", "green"};

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Circle circle = (Circle) ShapeFactory.getShape(getRandomColor());
            circle.setRadius(100);
            circle.setX(getRandomX());
            circle.setY(getRandomY());
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }

    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }

    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
