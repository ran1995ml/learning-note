package com.ran.leetcode.dp;

/**
 * HouseRobberII_213
 *
 * @author rwei
 * @since 2024/9/18 15:31
 */
public class HouseRobberII_213 {
    public static void main(String[] args) {
        HouseRobberII_213 obj = new HouseRobberII_213();
        int[] nums = {1, 2, 3};
        System.out.println(obj.rob(nums));
    }

    public int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(maxProfit(nums, 0, nums.length - 2), maxProfit(nums, 1, nums.length - 1));
    }

    private int maxProfit(int[] nums, int start, int end) {
        int[] dp = new int[end + 1];
        for (int i = start; i <= end; i++) {
            if (i == start) {
                dp[i] = nums[start];
            } else if (i == start + 1) {
                dp[i] = Math.max(nums[start], nums[start + 1]);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
            }
        }
        return dp[end];
    }
}
