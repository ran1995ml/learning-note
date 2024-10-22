package com.ran.pattern.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * CareTaker
 * 负责人，管理备忘录
 * @author rwei
 * @since 2024/9/24 15:15
 */
public class CareTaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void add(Memento memento) {
        mementoList.add(memento);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
