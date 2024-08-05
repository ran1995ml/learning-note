package com.ran.leetcode.stack;

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
        int[] heights = {2, 1, 2};
        System.out.println(obj.largestRectangleArea(heights));
    }

    public int largestRectangleArea(int[] heights) {
        int max = 0;
        int[] newHeights = new int[heights.length + 2];
        System.arraycopy(heights, 0, newHeights, 1, heights.length);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int cur = stack.pop();
                int h = newHeights[cur];
                int w = i - stack.peek() - 1;
                max = Math.max(max, w * h);
            }
            stack.add(i);
        }
        return max;
    }
}
