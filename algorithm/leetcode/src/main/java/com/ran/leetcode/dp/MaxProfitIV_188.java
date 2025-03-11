package com.ran.leetcode.dp;

/**
 * MaxProfitIV_188
 *
 * @author rwei
 * @since 2024/8/15 15:42
 */
public class MaxProfitIV_188 {
    public static void main(String[] args) {
        MaxProfitIV_188 obj = new MaxProfitIV_188();
        int[] prices = {3, 2, 6, 5, 0, 3};
        int k = 2;
        System.out.println(obj.maxProfit(k, prices));
    }

    public int maxProfit(int k, int[] prices) {
        int[][][] dp = new int[k + 1][prices.length][2];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < prices.length; j++) {
                if (j == 0) {
                    dp[i][j][1] = - prices[j];
                } else {
                    dp[i][j][0] = Math.max(dp[i][j - 1][0], dp[i][j - 1][1] + prices[j]);
                    dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[j], dp[i][j - 1][1]);
                }
            }
        }
        return dp[k][prices.length - 1][0];
    }
}
