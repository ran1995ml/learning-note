package com.ran.pattern.command;

/**
 * Door
 *
 * @author rwei
 * @since 2024/8/15 12:47
 */
public class Door {
    private String name;

    public Door(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + "on door");
    }

    public void off() {
        System.out.println(name + "off door");
    }
}
