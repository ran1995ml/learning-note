package com.ran.pattern.memento;

/**
 * Originator
 * 创建发起人，保存状态
 * @author rwei
 * @since 2024/9/24 15:09
 */
public class Originator {
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}
