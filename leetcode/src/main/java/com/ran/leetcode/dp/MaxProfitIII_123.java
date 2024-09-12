package com.ran.leetcode.dp;

/**
 * MaxProfitIII_123
 *
 * @author rwei
 * @since 2024/8/9 15:18
 */
public class MaxProfitIII_123 {
    public static void main(String[] args) {
        MaxProfitIII_123 obj = new MaxProfitIII_123();
        int[] prices = {3, 2, 6, 5, 0, 3};
        System.out.println(obj.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[4][2];
        for (int i = 0; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                if (i == 0) {
                    dp[k][1] = -prices[i];
                } else {
                    dp[k][0] = Math.max(dp[k][0], dp[k][1] + prices[i]);
                    dp[k][1] = Math.max(dp[k][1], dp[k - 1][0] - prices[i]);
                }
            }
        }
        return dp[2][0];
    }
}
