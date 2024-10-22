package com.ran.pattern.interpreter;

/**
 * Expression
 * 表达式接口
 * @author rwei
 * @since 2024/9/17 17:00
 */
public interface Expression {
    boolean interpret(String context);
}
