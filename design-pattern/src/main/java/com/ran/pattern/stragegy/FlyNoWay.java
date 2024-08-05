package com.ran.pattern.stragegy;

/**
 * FlyNoWay
 *
 * @author rwei
 * @since 2024/7/30 22:45
 */
public class FlyNoWay implements Fly {
    @Override
    public void fly() {
        System.out.println("fly no way");
    }
}
