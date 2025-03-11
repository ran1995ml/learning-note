package com.ran.leetcode.dp;

/**
 * UniquePaths_62
 *
 * @author rwei
 * @since 2024/6/12 11:08
 */
public class UniquePaths_62 {
    public static void main(String[] args) {
        UniquePaths_62 obj = new UniquePaths_62();
        int m = 3;
        int n = 7;
        System.out.println(obj.uniquePaths(m, n));
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
