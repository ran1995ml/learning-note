package com.ran.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * DailyTemperatures_739
 *
 * @author rwei
 * @since 2024/10/6 16:21
 */
public class DailyTemperatures_739 {
    public static void main(String[] args) {
        DailyTemperatures_739 obj = new DailyTemperatures_739();
        int[] temperatures = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(Arrays.toString(obj.dailyTemperatures(temperatures)));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? 0 : stack.peek() - i;
            stack.add(i);
        }
        return ans;
    }
}
