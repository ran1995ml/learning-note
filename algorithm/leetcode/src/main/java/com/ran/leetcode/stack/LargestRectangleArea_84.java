package com.ran.leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * LargestRectangleArea_84
 *
 * @author rwei
 * @since 2024/7/4 17:31
 */
public class LargestRectangleArea_84 {
    public static void main(String[] args) {
        LargestRectangleArea_84 obj = new LargestRectangleArea_84();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(obj.largestRectangleArea(heights));
    }

    public int largestRectangleArea(int[] heights) {
        int[] filledHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, filledHeights, 1, heights.length);
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i < filledHeights.length; i++) {
            while (!stack.isEmpty() && filledHeights[i] < filledHeights[stack.peek()]) {
                int h = filledHeights[stack.pop()];
                int w = i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }
            stack.push(i);
        }
        return max;
    }
}
