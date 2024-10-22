package com.ran.pattern.builder;

/**
 * Computer
 *
 * @author rwei
 * @since 2024/9/16 22:54
 */
public abstract class Computer {
    protected String board;

    protected String display;

    protected String os;

    protected Computer() {}


    public void setBoard(String board) {
        this.board = board;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "board='" + board + '\'' +
                ", display='" + display + '\'' +
                ", os='" + os + '\'' +
                '}';
    }
}
