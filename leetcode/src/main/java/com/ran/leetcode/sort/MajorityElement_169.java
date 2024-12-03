package com.ran.leetcode.sort;

import java.util.Random;

/**
 * MajorityElement_169
 *
 * @author rwei
 * @since 2024/9/30 14:21
 */
public class MajorityElement_169 {
    public static void main(String[] args) {
        MajorityElement_169 obj = new MajorityElement_169();
        int[] nums = {3, 2, 3};
        System.out.println(obj.majorityElement(nums));
    }

    public int majorityElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int index = quickSort(left, right, nums);
            if (index == nums.length / 2) {
                return nums[index];
            } else if (index < nums.length / 2) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
        return nums[left];
    }

    private int quickSort(int left, int right, int[] nums) {
        int target = new Random().nextInt(right - left + 1) + left;
        swap(nums, left, target);
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= temp) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] <= temp) left++;
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
