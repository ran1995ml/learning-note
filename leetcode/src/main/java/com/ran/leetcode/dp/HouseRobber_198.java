package com.ran.leetcode.dp;

import java.util.Arrays;

/**
 * HouseRobber_198
 *
 * @author rwei
 * @since 2024/9/12 14:05
 */
public class HouseRobber_198 {
    public static void main(String[] args) {
        HouseRobber_198 obj = new HouseRobber_198();
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(obj.rob(nums));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
    }
}
