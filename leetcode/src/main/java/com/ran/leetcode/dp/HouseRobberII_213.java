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
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(robWithRange(nums, 0, nums.length - 2), robWithRange(nums, 1, nums.length - 1));
    }

    private int robWithRange(int[] nums, int start, int end) {
        int[] dp = new int[end + 1];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[end];
    }
}
