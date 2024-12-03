package com.ran.leetcode.dp;

/**
 * PartitionEqualSubsetSum_416
 *
 * @author rwei
 * @since 2024/11/13 14:55
 */
public class PartitionEqualSubsetSum_416 {
    public static void main(String[] args) {
        PartitionEqualSubsetSum_416 obj = new PartitionEqualSubsetSum_416();
        int[] nums = {1, 2, 3, 5};
        System.out.println(obj.canPartition(nums));
    }

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 > 0) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                if (dp[target]) return true;
                dp[j] = dp[j] || dp[j - num];
            }
        }

        return dp[target];
    }
}
