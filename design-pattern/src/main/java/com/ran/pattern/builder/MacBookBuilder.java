package com.ran.pattern.builder;

/**
 * MacBookBuilder
 *
 * @author rwei
 * @since 2024/9/16 22:59
 */
public class MacBookBuilder extends Builder {
    private final Computer computer = new MacBook();

    @Override
    public Builder buildBoard(String board) {
        computer.setBoard(board);
        return this;
    }

    @Override
    public Builder buildDisplay(String display) {
        computer.setDisplay(display);
        return this;
    }

    @Override
    public Builder buildOs() {
        computer.setOs();
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}
