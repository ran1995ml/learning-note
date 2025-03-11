package com.ran.leetcode.stack;

import java.util.Stack;

/**
 * ValidParentheses_20
 *
 * @author rwei
 * @since 2024/5/19 21:12
 */
public class ValidParentheses_20 {
    public static void main(String[] args) {
        String s = "()[]{}";
        ValidParentheses_20 obj = new ValidParentheses_20();
        System.out.println(obj.isValid(s));
    }

    public boolean isValid(String s) {
        char[] ch = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : ch) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                if (c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else if (c == ']' && stack.peek() == '[') {
                    stack.pop();
                } else if (c == '}' && stack.peek() == '{') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }
        return stack.isEmpty();
    }
}
