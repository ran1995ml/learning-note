package com.ran.leetcode.dp;

import java.util.Arrays;

/**
 * LengthLIS_300
 *
 * @author rwei
 * @since 2024/10/10 10:00
 */
public class LengthLIS_300 {
    public static void main(String[] args) {
        LengthLIS_300 obj = new LengthLIS_300();
        int[] nums = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(obj.lengthOfLIS(nums));
    }

    public int lengthOfLIS(int[] nums) {
        int max = 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
