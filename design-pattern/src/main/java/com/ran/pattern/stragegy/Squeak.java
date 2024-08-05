package com.ran.pattern.stragegy;

/**
 * Squeak
 *
 * @author rwei
 * @since 2024/7/30 22:51
 */
public class Squeak implements Quack {
    @Override
    public void quack() {
        System.out.println("squeak");
    }
}
