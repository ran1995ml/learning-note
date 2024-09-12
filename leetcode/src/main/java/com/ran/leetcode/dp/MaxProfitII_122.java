package com.ran.leetcode.dp;

/**
 * MaxProfitII_122
 *
 * @author rwei
 * @since 2024/8/9 14:43
 */
public class MaxProfitII_122 {
    public static void main(String[] args) {
        MaxProfitII_122 obj = new MaxProfitII_122();
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(obj.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int sum = 0;
        for (int i = 0; i < prices.length; i++) {
            if (i - 1 >= 0 && prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }
}
