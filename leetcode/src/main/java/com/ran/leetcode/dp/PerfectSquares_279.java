package com.ran.leetcode.dp;

import java.util.Arrays;

/**
 * PerfectSquares_279
 *
 * @author rwei
 * @since 2024/10/9 16:38
 */
public class PerfectSquares_279 {
    public static void main(String[] args) {
        PerfectSquares_279 obj = new PerfectSquares_279();
        int n = 12;
        System.out.println(obj.numSquares(n));
    }

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            for (int j = i * i; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - i * i] + 1);
            }
        }
        return dp[n] == n + 1 ? 0 : dp[n];
    }
}
