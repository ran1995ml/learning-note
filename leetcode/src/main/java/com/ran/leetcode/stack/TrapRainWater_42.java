package com.ran.leetcode.stack;

import java.util.Stack;

/**
 * TrapRainWater_42
 *
 * @author rwei
 * @since 2024/5/30 13:54
 */
public class TrapRainWater_42 {
    public static void main(String[] args) {
        TrapRainWater_42 obj = new TrapRainWater_42();
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(obj.trap(height));
    }

    public int trap(int[] height) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int cur = stack.pop();
                if (stack.isEmpty()) break;
                int w = i - stack.peek() - 1;
                int h = Math.min(height[i], height[stack.peek()]) - height[cur];
                sum += w * h;
            }
            stack.add(i);
        }
        return sum;
    }
}
