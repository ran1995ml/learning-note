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

    private Stack<Integer> stack;

    private Stack<Integer> stackMin;

    public MinStack_155() {
        stack = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(val);
            stackMin.push(val);
        } else {
            if (val <= stackMin.peek()) {
                stackMin.push(val);
                stack.push(val);
            }
        }
    }

    public void pop() {
        if (stack.isEmpty()) throw new IllegalStateException("stack is empty");
        int cur = stack.pop();
        if (stackMin.peek() == cur) {
            stackMin.pop();
        }
    }

    public int top() {
        if (stack.isEmpty()) throw new IllegalStateException("stack is empty");
        return stack.peek();
    }

    public int getMin() {
        if (stackMin.isEmpty()) throw new IllegalStateException("min stack is empty");
        return stackMin.peek();
    }
}
