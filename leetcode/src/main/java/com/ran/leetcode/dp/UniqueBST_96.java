package com.ran.leetcode.dp;

/**
 * UniqueBST_96
 * 1 2 3 4
 * s(1) = f(0) * f(3)
 * s(2) = f(1) * f(2)
 * s(3) = f(2) * f(1)
 * s(4) = f(3) * f(0)
 * g(n) = f(0) * f(n-1) + f(1) * f(n-2) + f(2) * f(n-3) + ... + f(n-1) * f(0)
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
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - 1 - j];
            }
        }

        return dp[n];
    }
}
