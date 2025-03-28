package com.ran.leetcode.array;

/**
 * RemoveElement_27
 *
 * @author rwei
 * @since 2024/5/20 16:40
 */
public class RemoveElement_27 {
    public static void main(String[] args) {
        RemoveElement_27 obj = new RemoveElement_27();
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        System.out.println(obj.removeElement(nums, val));

    }

    public int removeElement(int[] nums, int val) {
        int p = 0;
        for (int num : nums) {
            if (num != val) nums[p++] = num;
        }
        return p;
    }
}
