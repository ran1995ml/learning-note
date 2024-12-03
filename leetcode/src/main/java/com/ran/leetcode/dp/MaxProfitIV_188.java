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
        int[][] dp = new int[k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 0) {
                    dp[j][1] = - prices[i];
                } else {
                    dp[j][0] = Math.max(dp[j][0], dp[j][1] + prices[i]);
                    dp[j][1] = Math.max(dp[j][1], dp[j - 1][0] - prices[i]);
                }
            }
        }
        return dp[k][0];
    }
}
