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
        this.stack = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public void push(int val) {
        this.stack.push(val);
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(val);
        } else {
            if (val <= this.stackMin.peek()) {
                this.stackMin.push(val);
            }
        }
    }

    public void pop() {
        if (this.stack.isEmpty()) {
            throw new RuntimeException("empty stack");
        } else {
            int val = this.stack.pop();
            if (val == this.stackMin.peek()) {
                this.stackMin.pop();
            }
        }
    }

    public int top() {
        return this.stack.peek();
    }

    public int getMin() {
        return this.stackMin.peek();
    }
}
