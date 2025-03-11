package com.ran.algorithm.stack;

import java.util.*;

/**
 * Evaluate
 * (1 + ((2 + 3) * (4 * 5)))
 * 模拟四则运算，准备两个栈，一个放操作数，一个放运算符
 * 1.操作数和运算符分别入栈
 * 2.忽略左括号
 * 3.遇到右括号弹出操作数和运算符，计算结果插入操作数栈
 * 4.栈中剩余的最后一个元素是结果
 * @author rwei
 * @since 2025/2/18 16:25
 */
public class Evaluate {
    public static void main(String[] args) {
        String s = "(1+((2+3)*(4*5)))";
        System.out.println(compute(s));
    }

    public static int compute(String s) {
        Set<Character> set = new HashSet<>();
        set.add('*');
        set.add('/');
        set.add('+');
        set.add('-');

        Stack<Character> stackOps = new Stack<>();
        Stack<Integer> stackNum = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                stackNum.push(c - '0');
            } else if (set.contains(c)) {
                stackOps.push(c);
            } else if (c == ')') {
                char ops = stackOps.pop();
                int num = stackNum.pop();
                if (ops == '+') {
                    num = stackNum.pop() + num;
                } else if (ops == '-') {
                    num = stackNum.pop() - num;
                } else if (ops == '*') {
                    num = stackNum.pop() * num;
                } else if (ops == '/') {
                    num = stackNum.pop() / num;
                }
                stackNum.push(num);
            }
        }
        return stackNum.peek();
    }
}
