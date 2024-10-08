package com.ran.pattern.adapter;

/**
 * TurkeyAdapter
 * 实现想要转换的接口
 *
 * @author rwei
 * @since 2024/8/15 14:31
 */
public class TurkeyAdapter implements Duck {
    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
