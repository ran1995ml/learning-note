package com.ran.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * ShapeFactory
 * 蝇量工厂
 * @author rwei
 * @since 2024/9/18 11:20
 */
public class ShapeFactory {
    private static final Map<String, Shape> map = new HashMap<>();

    public static Shape getShape(String color) {
        Shape shape = map.get(color);
        if (shape == null) {
            shape = new Circle(color);
            map.put(color, shape);
            System.out.println("create shape for color " + color);
        }
        return shape;
    }
}
