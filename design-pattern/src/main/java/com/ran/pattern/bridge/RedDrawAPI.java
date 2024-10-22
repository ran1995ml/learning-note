package com.ran.pattern.bridge;

/**
 * RedDrawAPI
 *
 * @author rwei
 * @since 2024/9/16 20:19
 */
public class RedDrawAPI implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.printf("red: %s, %s, %s%n", radius, x, y);
    }
}
