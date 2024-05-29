package com.ran.leetcode.math;

import java.util.Arrays;

/**
 * NextPermutation_31
 *
 * @author rwei
 * @since 2024/5/29 09:43
 */
public class NextPermutation_31 {
    public static void main(String[] args) {
        NextPermutation_31 obj = new NextPermutation_31();
        int[] nums = {1, 1, 5};
        obj.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        while (i - 1 >= 0 && nums[i] <= nums[i - 1]) {
            i--;
        }
        if (i == 0) {
            reverse(nums, 0, nums.length - 1);
        } else {
            i = i - 1;
            int j = nums.length - 1;
            while (j > i && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
            reverse(nums, i + 1, nums.length - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}
