package com.ran.leetcode.dp;

import java.util.Arrays;

/**
 * UniqueBST_96
 * 1 2 3 4
 * s(4) = s(0) * s(3) + s(1) * s(2) + s(2) * s(1) + s(3) * s(0)
 * s(n) = s(0) * s(n-1) + s(1) * s(n-2) + ... + s(n-1) * s(0)
 *
 * @author rwei
 * @since 2024/7/24 15:52
 */
public class UniqueBST_96 {
    public static void main(String[] args) {
        UniqueBST_96 obj = new UniqueBST_96();
        int n = 5;
        System.out.println(obj.numTrees(n));
    }

    public int numTrees(int n) {
        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] = dp[i] + dp[j] * dp[i - 1 - j];
            }
        }
        return dp[n];
    }
}
