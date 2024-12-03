package com.ran.leetcode.dp;

/**
 * MaxProfitCoolDown_309
 *
 * @author rwei
 * @since 2024/10/10 10:19
 */
public class MaxProfitCoolDown_309 {
    public static void main(String[] args) {
        MaxProfitCoolDown_309 obj = new MaxProfitCoolDown_309();
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(obj.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                dp[i][1] = - prices[i];
            } else if (i == 1) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][0];
    }
}
