package com.ran.pattern.mediator;

/**
 * User
 * 同事类
 * @author rwei
 * @since 2024/9/20 13:58
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage(String message) {
        ChatRoom.showMessage(this, message);
    }
}
