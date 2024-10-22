package com.ran.pattern.chain;

/**
 * ErrorLogger
 *
 * @author rwei
 * @since 2024/9/18 10:18
 */
public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    public void write(String message) {
        System.out.printf("error: %s%n", message);
    }
}
