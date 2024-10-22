package com.ran.pattern.mediator;

/**
 * Main
 * 中介者模式
 * @author rwei
 * @since 2024/9/20 13:57
 */
public class Main {
    public static void main(String[] args) {
        User john = new User("john");
        User tom = new User("tom");
        john.sendMessage("Hello, I'm John!");
        tom.sendMessage("Hellp, I'm Tom!");
    }
}
