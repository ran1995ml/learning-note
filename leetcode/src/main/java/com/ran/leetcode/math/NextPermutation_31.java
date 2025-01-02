package com.ran.leetcode.math;

import java.util.Arrays;

/**
 * NextPermutation_31
 * 从低位查找递增序列
 *
 * @author rwei
 * @since 2024/5/29 09:43
 */
public class NextPermutation_31 {
    public static void main(String[] args) {
        NextPermutation_31 obj = new NextPermutation_31();
        int[] nums = {5, 4, 2, 3, 3, 1}; // [5, 4, 3, 1, 2, 3]
//        int[] nums = {3, 2, 1};
        obj.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void nextPermutation(int[] nums) {
        int p1 = nums.length - 1;
        while (p1 - 1 >= 0 &&nums[p1 - 1] >= nums[p1]) p1--;
        if (p1 == 0) {
            rotate(nums, 0, nums.length - 1);
            return;
        }
        p1 = p1 - 1;
        int p2 = nums.length - 1;
        while (nums[p2] <= nums[p1]) p2--;
        swap(nums, p1, p2);
        rotate(nums, p1 + 1, nums.length - 1);
    }

    private void rotate(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
