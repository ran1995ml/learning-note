package com.ran.pattern.adapter;

/**
 * WildTurkey
 *
 * @author rwei
 * @since 2024/8/15 14:29
 */
public class WildTurkey implements Turkey {
    @Override
    public void gobble() {
        System.out.println("wild turkey gobble");
    }

    @Override
    public void fly() {
        System.out.println("wild turkey fly");
    }
}
