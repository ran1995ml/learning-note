package com.ran.pattern.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * ShapeCache
 *
 * @author rwei
 * @since 2024/9/26 11:31
 */
public class ShapeCache {
    private static final Map<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String shapeId) throws CloneNotSupportedException {
        Shape shape = shapeMap.get(shapeId);
        return (Shape) shape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);
    }
}
