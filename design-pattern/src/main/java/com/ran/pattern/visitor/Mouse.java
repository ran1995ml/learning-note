package com.ran.pattern.visitor;

/**
 * Mouse
 *
 * @author rwei
 * @since 2024/9/26 13:35
 */
public class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
