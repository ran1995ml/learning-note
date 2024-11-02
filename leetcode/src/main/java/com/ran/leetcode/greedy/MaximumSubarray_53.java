package com.ran.leetcode.greedy;

/**
 * MaximumSubarray_53
 * sum[i] = sum[i-1] + num[i] sum[i-1] > 0
 * sum[i] = num[i] sum[i-1] < 0
 * @author rwei
 * @since 2024/6/3 15:06
 */
public class MaximumSubarray_53 {
    public static void main(String[] args) {
        MaximumSubarray_53 obj = new MaximumSubarray_53();
        int[] nums = {5, 4, -1, 7, 8};
        System.out.println(obj.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int num : nums) {
            sum = sum < 0 ? num : sum + num;
            max = Math.max(max, sum);
        }
        return max;
    }
}
