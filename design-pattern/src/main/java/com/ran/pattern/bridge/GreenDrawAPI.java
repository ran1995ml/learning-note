package com.ran.pattern.bridge;

/**
 * GreenDrawAPI
 *
 * @author rwei
 * @since 2024/9/16 20:20
 */
public class GreenDrawAPI implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.printf("green: %s, %s, %s%n", radius, x, y);
    }
}
