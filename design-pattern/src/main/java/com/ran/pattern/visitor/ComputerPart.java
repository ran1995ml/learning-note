package com.ran.pattern.visitor;

/**
 * ComputerPart
 *
 * @author rwei
 * @since 2024/9/26 13:32
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
