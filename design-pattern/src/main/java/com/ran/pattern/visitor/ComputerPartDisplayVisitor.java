package com.ran.pattern.visitor;

/**
 * ComputerPartDisplayVisitor
 *
 * @author rwei
 * @since 2024/9/26 13:39
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("keyboard");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("mouse");
    }
}
