package com.ran.leetcode.greedy;

/**
 * MaximumSubarray_53
 *
 * @author rwei
 * @since 2024/6/3 15:06
 */
public class MaximumSubarray_53 {
    public static void main(String[] args) {
        MaximumSubarray_53 obj = new MaximumSubarray_53();
        int[] nums = {5,4,-1,7,8};
        System.out.println(obj.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;

        for (int num : nums) {
            if (sum < 0) {
                sum = num;
            } else {
                sum += num;
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
