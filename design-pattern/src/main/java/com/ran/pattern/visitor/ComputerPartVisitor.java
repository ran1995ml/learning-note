package com.ran.pattern.visitor;

/**
 * ComputerPartVisitor
 *
 * @author rwei
 * @since 2024/9/26 13:33
 */
public interface ComputerPartVisitor {
    void visit(Keyboard keyboard);

    void visit(Mouse mouse);
}
