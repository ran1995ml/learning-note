package com.ran.leetcode.math;

/**
 * SingleNumber_136
 *
 * @author rwei
 * @since 2024/7/26 11:01
 */
public class SingleNumber_136 {
    public static void main(String[] args) {
        SingleNumber_136 obj = new SingleNumber_136();
        int[] nums = {4, 1, 2, 1, 2};
        System.out.println(obj.singleNumber(nums));
    }

    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans = ans ^ num;
        }
        return ans;
    }
}
