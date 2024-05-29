package com.ran.leetcode.search;

/**
 * Search_33
 *
 * @author rwei
 * @since 2024/5/29 10:09
 */
public class Search_33 {
    public static void main(String[] args) {
        Search_33 obj = new Search_33();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;
        System.out.println(obj.search(nums, target));
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] >= nums[left]) {
                if (target < nums[mid] && target >= nums[left]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (nums[mid] <= nums[right]) {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
