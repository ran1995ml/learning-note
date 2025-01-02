package com.ran.leetcode.search;

/**
 * FindMin_153
 *
 * @author rwei
 * @since 2024/12/23 10:15
 */
public class FindMin_153 {
    public static void main(String[] args) {
        FindMin_153 obj = new FindMin_153();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
//        int[] nums = {11, 13, 15, 17};
        System.out.println(obj.findMin(nums));
    }

    public int findMin(int[] nums) {
        if (nums[nums.length - 1] > nums[0]) {
            return nums[0];
        } else {
            int left = 0;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (nums[mid] < nums[0]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return nums[right];
        }
    }
}
