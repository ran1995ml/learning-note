package com.ran.pattern.chain;

/**
 * AbstractLogger
 *
 * @author rwei
 * @since 2024/9/18 10:11
 */
public abstract class AbstractLogger {
    public static int INFO = 1;

    public static int DEBUG = 2;

    public static int ERROR = 3;

    protected int level;

    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level == level) {
            write(message);
        } else {
            if (nextLogger != null) {
                nextLogger.logMessage(level, message);
            }
        }
    }

    public abstract void write(String message);
}
