package com.ran.leetcode.dp;

/**
 * MaxProfit_121
 *
 * @author rwei
 * @since 2024/8/9 14:32
 */
public class MaxProfit_121 {
    public static void main(String[] args) {
        MaxProfit_121 obj = new MaxProfit_121();
        int[] prices = {7, 6, 4, 3, 1};
        System.out.println(obj.maxProfit(prices));
    }

    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int price : prices) {
            min = Math.min(min, price);
            max = Math.max(max, price - min);
        }
        return max;
    }
}
