package com.ran.pattern.prototype;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/26 13:16
 */
public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        ShapeCache.loadCache();
        Shape shape1 = ShapeCache.getShape("1");
        Shape shape2 = ShapeCache.getShape("2");
        shape1.draw();
        shape2.draw();
    }
}
