package com.ran.pattern.stragegy;

/**
 * Mute
 *
 * @author rwei
 * @since 2024/7/30 22:52
 */
public class Mute implements Quack {
    @Override
    public void quack() {
        System.out.println("mute");
    }
}
