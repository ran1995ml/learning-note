package com.ran.leetcode.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * SortArray_912
 *
 * @author rwei
 * @since 2024/9/26 11:18
 */
public class SortArray_912 {
    public static void main(String[] args) {
        SortArray_912 obj = new SortArray_912();
        int[] nums = {5, 1, 1, 2, 0, 0};
        obj.bubbleSort(nums);
//        obj.quickSort(nums, 0, nums.length - 1);
//        obj.heapSort(nums);
//        System.out.println(Arrays.toString(obj.mergeSort(nums)));
        System.out.println(Arrays.toString(nums));
    }

    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    sorted = false;
                }
            }
            if (sorted) break;
        }
    }

    public void quickSort(int[] nums, int low, int high) {
        if (low >= high) return;
        int left = low;
        int right = high;
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
        quickSort(nums, low, left - 1);
        quickSort(nums, left + 1, high);
    }

    public int[] mergeSort(int[] nums) {
        if (nums.length <= 1) return nums;
        int[] left = Arrays.copyOfRange(nums, 0, nums.length / 2);
        int[] right = Arrays.copyOfRange(nums, nums.length / 2, nums.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] nums = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        for (int k = 0; k < nums.length; k++) {
            if (i < left.length && j < right.length) {
                nums[k] = left[i] < right[j] ? left[i++] : right[j++];
            } else if (i < left.length) {
                nums[k] = left[i++];
            } else {
                nums[k] = right[j++];
            }
        }
        return nums;
    }

    public void heapSort(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            adjustHeap(nums, 0, i);
        }
    }

    private void adjustHeap(int[] nums, int i, int length) {
        int temp = nums[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && nums[k + 1] > nums[k]) k++;
            if (nums[k] > temp) {
                nums[i] = nums[k];
                i = k;
            } else {
                break;
            }
        }
        nums[i] = temp;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
