package com.ran.leetcode.search;

/**
 * Search_33
 * 判断元素是在有序部分还是在无序部分
 * @author rwei
 * @since 2024/5/29 10:09
 */
public class Search_33 {
    public static void main(String[] args) {
        Search_33 obj = new Search_33();
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        System.out.println(obj.search(nums, target));
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                if (nums[mid] > nums[nums.length - 1] && nums[nums.length - 1] >= target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else {
                if (nums[mid] < nums[0] && nums[0] <= target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
