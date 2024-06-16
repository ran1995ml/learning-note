package com.ran.leetcode.dp;

/**
 * ClimbStairs_70
 *
 * @author rwei
 * @since 2024/6/12 14:29
 */
public class ClimbStairs_70 {
    public static void main(String[] args) {
        ClimbStairs_70 obj = new ClimbStairs_70();
        int n = 4;
        System.out.println(obj.climbStairs(n));
    }

    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            if (i <= 2) {
                dp[i] = i;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        return dp[n];
    }
}
