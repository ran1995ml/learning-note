package com.ran.pattern.command;

/**
 * Light
 * 请求接收执行者
 *
 * @author rwei
 * @since 2024/8/13 22:43
 */
public class Light {
    private String name;

    public Light(String name) {
        this.name = name;
    }

    public void on() {
        System.out.println(name + "on light");
    }

    public void off() {
        System.out.println(name + "off light");
    }
}
