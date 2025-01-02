package com.ran.leetcode.sort;

import java.util.Arrays;

/**
 * SortColors_75
 *
 * @author rwei
 * @since 2024/6/25 16:41
 */
public class SortColors_75 {
    public static void main(String[] args) {
        SortColors_75 obj = new SortColors_75();
        int[] nums = {2, 0, 2, 1, 1, 0};
        obj.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void sortColors(int[] nums) {
        int p0 = 0;
        int p2 = nums.length - 1;
        int p1 = 0;
        while (p1 <= p2) {
            if (nums[p1] == 0) {
                swap(nums, p1, p0);
                p0++;
                p1++;
            } else if (nums[p1] == 2) {
                swap(nums, p1, p2);
                p2--;
            } else {
                p1++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
