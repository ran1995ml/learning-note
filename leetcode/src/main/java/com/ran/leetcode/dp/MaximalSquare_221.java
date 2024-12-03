package com.ran.leetcode.dp;

/**
 * MaximalSquare_221
 *
 * @author rwei
 * @since 2024/9/20 10:09
 */
public class MaximalSquare_221 {
    public static void main(String[] args) {
        MaximalSquare_221 obj = new MaximalSquare_221();
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(obj.maximalSquare(matrix));
    }

    public int maximalSquare(char[][] matrix) {
        int max = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
