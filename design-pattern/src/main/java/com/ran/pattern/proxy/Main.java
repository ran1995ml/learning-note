package com.ran.pattern.proxy;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/9 10:25
 */
public class Main {
    public static void main(String[] args) {
        String fileName = "test.jpg";
        Image image = new ProxyImage(fileName);
        image.display();
    }
}
