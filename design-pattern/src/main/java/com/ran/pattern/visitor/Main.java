package com.ran.pattern.visitor;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/26 13:37
 */
public class Main {
    public static void main(String[] args) {
        ComputerPart mouse = new Mouse();
        ComputerPart keyboard = new Keyboard();
        mouse.accept(new ComputerPartDisplayVisitor());
        keyboard.accept(new ComputerPartDisplayVisitor());
    }
}
