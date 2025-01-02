package com.ran.leetcode.dp;

import java.util.Arrays;

/**
 * JumpGameII_45
 *
 * @author rwei
 * @since 2024/12/25 10:30
 */
public class JumpGameII_45 {
    public static void main(String[] args) {
        JumpGameII_45 obj = new JumpGameII_45();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(obj.jump(nums));
    }

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, nums.length + 1);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < i + nums[i]; j++) {
                int index = j < nums.length ? j : nums.length - 1;
                dp[index] = Math.min(dp[index], dp[i] + 1);
            }
        }
        return dp[nums.length - 1];
    }

    public int jump1(int[] nums) {
        int end = 0;
        int max = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                end = max;
                steps++;
            }
        }
        return steps;
    }
}
