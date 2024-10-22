package com.ran.pattern.builder;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/16 23:01
 */
public class Main {
    public static void main(String[] args) {
        Builder builder = new MacBookBuilder();
        Computer computer = builder.buildBoard("machine board")
                .buildDisplay("big")
                .buildOs()
                .build();
        System.out.println(computer);
    }
}
