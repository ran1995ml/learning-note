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
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                dp[i] = nums[0];
            } else if (i == 1) {
                dp[i] = Math.max(nums[0], nums[1]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }
        return dp[nums.length - 1];
    }
}
