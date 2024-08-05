package com.ran.pattern.stragegy;

/**
 * FlyWithWings
 *
 * @author rwei
 * @since 2024/7/30 22:42
 */
public class FlyWithWings implements Fly {
    @Override
    public void fly() {
        System.out.println("fly with wings");
    }
}
