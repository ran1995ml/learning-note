package com.ran.leetcode.dp;

/**
 * MaxProduct_152
 *
 * @author rwei
 * @since 2024/11/10 22:14
 */
public class MaxProduct_152 {
    public static void main(String[] args) {
        MaxProduct_152 obj = new MaxProduct_152();
        int[] nums = {2, 3, -2, 4};
        System.out.println(obj.maxProduct(nums));
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int curMax = 1;
        int curMin = 1;
        for (int num : nums) {
            if (num < 0) {
                int temp = curMin;
                curMin = curMax;
                curMax = temp;
            }
            curMax = Math.max(num, curMax * num);
            curMin = Math.min(num, curMin * num);
            max = Math.max(max, curMax);
        }
        return max;
    }
}
