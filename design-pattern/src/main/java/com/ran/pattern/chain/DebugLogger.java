package com.ran.pattern.chain;

/**
 * DebugLogger
 *
 * @author rwei
 * @since 2024/9/18 10:17
 */
public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    public void write(String message) {
        System.out.printf("debug: %s%n", message);
    }
}
