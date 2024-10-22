package com.ran.pattern.builder;

/**
 * MacBook
 *
 * @author rwei
 * @since 2024/9/16 22:56
 */
public class MacBook extends Computer {
    protected MacBook() {}

    @Override
    public void setOs() {
        os = "mac";
    }
}
