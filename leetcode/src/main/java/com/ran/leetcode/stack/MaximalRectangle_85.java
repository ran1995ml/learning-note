package com.ran.leetcode.stack;

import java.util.Stack;

/**
 * MaximalRectangle_85
 *
 * @author rwei
 * @since 2024/7/8 15:22
 */
public class MaximalRectangle_85 {
    public static void main(String[] args) {
        MaximalRectangle_85 obj = new MaximalRectangle_85();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
//        char[][] matrix = {{'1', '0'}, {'1', '0'}};
        System.out.println(obj.maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        int[] height = new int[matrix[0].length];
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (chars[j] == '0') {
                    height[j] = 0;
                } else {
                    height[j] += 1;
                }
            }
            max = Math.max(max, maxArea(height));
        }
        return max;
    }

    private int maxArea(int[] height) {
        int max = 0;
        int[] filledHeight = new int[height.length + 2];
        System.arraycopy(height, 0, filledHeight, 1, height.length);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < filledHeight.length; i++) {
            while (!stack.isEmpty() && filledHeight[i] < filledHeight[stack.peek()]) {
                int cur = stack.pop();
                int h = filledHeight[cur];
                int w = i - stack.peek() - 1;
                max = Math.max(max, w * h);
            }
            stack.add(i);
        }
        return max;
    }
}
