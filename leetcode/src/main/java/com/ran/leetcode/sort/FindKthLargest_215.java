package com.ran.leetcode.sort;

/**
 * FindKthLargest_215
 *
 * @author rwei
 * @since 2024/9/30 15:07
 */
public class FindKthLargest_215 {
    public static void main(String[] args) {
        FindKthLargest_215 obj = new FindKthLargest_215();
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(obj.findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int index = quickSort(nums, left, right);
            if (index == k - 1) {
                return nums[index];
            } else if (index > k - 1) {
                right = index - 1;
            } else {
                left = index + 1;
            }
        }
        return -1;
    }

    private int quickSort(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] <= temp) right--;
            nums[left] = nums[right];
            while (left < right && nums[left] >= temp) left++;
            nums[right] = nums[left];
        }
        nums[left] = temp;
        return left;
    }
}
