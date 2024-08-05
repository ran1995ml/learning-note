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
//        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        char[][] matrix = {{'1', '0'}, {'1', '0'}};
        System.out.println(obj.maximalRectangle(matrix));
    }

    public int maximalRectangle(char[][] matrix) {
        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];

        for (int i = row - 1; i >= 0; i--) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    heights[j] += 1;
                }
            }
            max = Math.max(max, maxArea(heights));
        }
        return max;
    }

    private int maxArea(int[] heights) {
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
