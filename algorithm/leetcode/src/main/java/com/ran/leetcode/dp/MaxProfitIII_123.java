package com.ran.leetcode.dp;

/**
 * MaxProfitIII_123
 * S(i)(k) 第i天，第k次购买
 * S(i)(k)(0) = Math.max(S(i-1)(k)(0), S(i-1)(k)(1)+prices[i])
 * S(i)(k)(1) = Math.max(S(i-1)(k)(1), S(i-1)(k-1)(0)-prices[i])
 * @author rwei
 * @since 2024/8/9 15:18
 */
public class MaxProfitIII_123 {
    public static void main(String[] args) {
        MaxProfitIII_123 obj = new MaxProfitIII_123();
        int[] prices = {1, 2, 3, 4, 5};
        System.out.println(obj.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int[][] dp = new int[3][2];

        for (int i = 0; i < prices.length; i++) {
            for (int k = 1; k <= 2; k++) {
                if (i == 0) {
                    dp[k][1] = - prices[i];
                } else {
                    dp[k][0] = Math.max(dp[k][0], dp[k][1] + prices[i]);
                    dp[k][1] = Math.max(dp[k][1], dp[k - 1][0] - prices[i]);
                }
            }
        }
        return dp[2][0];
    }
}
