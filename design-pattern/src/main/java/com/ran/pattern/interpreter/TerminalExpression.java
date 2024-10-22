package com.ran.pattern.interpreter;

/**
 * TerminalExpression
 *
 * @author rwei
 * @since 2024/9/17 17:02
 */
public class TerminalExpression implements Expression {
    private final String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}
