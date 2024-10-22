package com.ran.pattern.memento;

/**
 * Memento
 * 备忘录类
 * @author rwei
 * @since 2024/9/24 15:05
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
