package com.ran.leetcode.stack;

import java.util.Stack;

/**
 * MinStack_155
 *
 * @author rwei
 * @since 2024/9/23 14:05
 */
public class MinStack_155 {
    public static void main(String[] args) {
        MinStack_155 stack = new MinStack_155();
        stack.push(-2);
        stack.push(0);
        stack.push(-3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.top());
        System.out.println(stack.getMin());
    }

    private final Stack<Integer> stack;

    private final Stack<Integer> stackMin;

    public MinStack_155() {
        stack = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int val) {
        if (stackMin.isEmpty()) {
            stackMin.push(val);
        } else if (val <= stackMin.peek()) {
            stackMin.push(val);
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("empty stack");
        }
        int val = stack.pop();
        if (val == stackMin.peek()) stackMin.pop();
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new RuntimeException("empty stack");
        } else {
            return stack.peek();
        }
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("empty stack");
        } else {
            return stackMin.peek();
        }
    }
}
