package com.ran.leetcode.dp;

/**
 * TargetSum_494
 * sum+ - sum- = target
 * sum+ + sum- = sum
 * 装的下，j - nums[i] >= 0
 * dp[j] = dp[j] + dp[j - nums[i]]
 * 装不下，j - nums[i] < 0
 * dp[j] = dp[j]
 * @author rwei
 * @since 2024/10/9 16:46
 */
public class TargetSum_494 {
    public static void main(String[] args) {
        TargetSum_494 obj = new TargetSum_494();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(obj.findTargetSumWays(nums, target));
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum + target) % 2 > 0 || sum + target < 0) return 0;
        int count = (sum + target) / 2;
        int[][] dp = new int[nums.length + 1][count + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 0; j <= count; j++) {
                dp[i][j] = j >= nums[i - 1] ? dp[i - 1][j] + dp[i - 1][j - nums[i - 1]] : dp[i - 1][j];
            }
        }
        return dp[nums.length][count];
    }

    public int findTargetSumWays1(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum + target) % 2 > 0 || sum + target < 0) return 0;
        int count = (sum + target) / 2;
        int[] dp = new int[count + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int j = count; j >= num; j--) {
                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[count];
    }
}
