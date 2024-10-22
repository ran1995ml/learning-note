package com.ran.pattern.chain;

/**
 * InfoLogger
 *
 * @author rwei
 * @since 2024/9/18 10:15
 */
public class InfoLogger extends AbstractLogger {
    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    public void write(String message) {
        System.out.printf("info: %s%n", message);
    }
}
