package com.ran.java.effective.item_3;

import java.io.Serializable;

/**
 * Elvis2
 *
 * @author rwei
 * @since 2024/4/22 21:34
 */
public class Elvis2 implements Serializable {
    private static final transient Elvis2 instance = new Elvis2();

    private Elvis2() {}

    public static Elvis2 getInstance() {
        return instance;
    }

    private Object readSolve() {
        return instance;
    }
}
