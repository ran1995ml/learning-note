package com.ran.leetcode.sort;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * FindKthLargest_215
 * 使用PriorityQueue小顶堆的特效，poll会将堆顶元素抛出
 * @author rwei
 * @since 2024/9/30 15:07
 */
public class FindKthLargest_215 {
    public static void main(String[] args) {
        FindKthLargest_215 obj = new FindKthLargest_215();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(obj.findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int index = quickSort(nums, left, right);
            if (index == nums.length - k) {
                return nums[index];
            } else if (index < nums.length - k) {
                left = index + 1;
            } else {
                right = index - 1;
            }
        }
        return -1;
    }

    private int quickSort(int[] nums, int left, int right) {
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
