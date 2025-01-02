package com.ran.leetcode.dp;

import java.util.Arrays;

/**
 * CoinChange_322
 * 完全背包问题
 * @author rwei
 * @since 2024/10/8 15:38
 */
public class CoinChange_322 {
    public static void main(String[] args) {
        CoinChange_322 obj = new CoinChange_322();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(obj.coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
