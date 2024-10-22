package com.ran.pattern.visitor;

/**
 * Keyboard
 *
 * @author rwei
 * @since 2024/9/26 13:33
 */
public class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
