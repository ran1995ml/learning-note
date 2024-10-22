package com.ran.pattern.mediator;

import java.util.Date;

/**
 * ChatRoom
 *
 * @author rwei
 * @since 2024/9/20 13:58
 */
public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.printf("%s %s: %s%n", new Date(), user.getName(), message);
    }
}
