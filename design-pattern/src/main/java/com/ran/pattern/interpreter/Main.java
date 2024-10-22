package com.ran.pattern.interpreter;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/17 17:36
 */
public class Main {
    public static Expression male() {
        Expression robert = new TerminalExpression("robert");
        Expression john = new TerminalExpression("john");
        return new OrExpression(robert, john);
    }

    public static Expression marriedWoman() {
        Expression jane = new TerminalExpression("jane");
        Expression married = new TerminalExpression("married");
        return new AndExpression(jane, married);
    }

    public static void main(String[] args) {
        Expression male = male();
        Expression marriedWoman = marriedWoman();
        System.out.println(male.interpret("john"));
        System.out.println(marriedWoman.interpret("man"));
    }
}
