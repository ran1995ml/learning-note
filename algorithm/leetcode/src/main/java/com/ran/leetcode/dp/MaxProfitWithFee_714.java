package com.ran.leetcode.dp;

/**
 * MaxProfitWithFee_714
 *
 * @author rwei
 * @since 2024/10/6 16:12
 */
public class MaxProfitWithFee_714 {
    public static void main(String[] args) {
        MaxProfitWithFee_714 obj = new MaxProfitWithFee_714();
        int[] prices = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(obj.maxProfit(prices, fee));
    }

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                dp[i][1] = - prices[i];
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][0];
    }
}
