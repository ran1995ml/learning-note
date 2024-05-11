package com.ran.java.effective.item_3;

import java.io.Serializable;

/**
 * Elvis1
 *
 * @author rwei
 * @since 2024/4/22 21:29
 */
public class Elvis1 implements Serializable {
    public static final transient Elvis1 instance = new Elvis1();

    private Elvis1() {}
}
