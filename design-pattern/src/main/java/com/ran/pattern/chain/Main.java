package com.ran.pattern.chain;

/**
 * Main
 * 责任链模式
 * @author rwei
 * @since 2024/9/18 10:10
 */
public class Main {
    public static AbstractLogger getChainLoggers() {
        ErrorLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);
        DebugLogger debugLogger = new DebugLogger(AbstractLogger.DEBUG);
        InfoLogger infoLogger = new InfoLogger(AbstractLogger.INFO);
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(infoLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        AbstractLogger logger = getChainLoggers();
        logger.logMessage(AbstractLogger.INFO, "info message");
        logger.logMessage(AbstractLogger.DEBUG, "debug message");
        logger.logMessage(AbstractLogger.ERROR, "error message");
    }
}
