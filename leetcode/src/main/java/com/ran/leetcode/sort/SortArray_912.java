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
//        obj.bubbleSort(nums);
        obj.quickSort(nums, 0, nums.length - 1);
//        obj.heapSort(nums);
//        System.out.println(Arrays.toString(obj.mergeSort(nums)));
        System.out.println(Arrays.toString(nums));
    }

    public void bubbleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
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

    public void heapSort(int[] nums) {
        for (int i = nums.length / 2; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }

        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            adjustHeap(nums, 0, i);
        }
    }

    private void adjustHeap(int[] nums, int rootIndex, int length) {
        int temp = nums[rootIndex];
        for (int i = 2 * rootIndex + 1; i < length; i = 2 * i + 1) {
            if (i + 1 < length && nums[i + 1] > nums[i]) i++;
            if (nums[i] > temp) {
                nums[rootIndex] = nums[i];
                rootIndex = i;
            } else {
                break;
            }
        }
        nums[rootIndex] = temp;
    }

    private int[] merge(int[] left, int[] right) {
        int[] nums = new int[left.length + right.length];
        int p1 = 0;
        int p2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (p1 < left.length && p2 < right.length) {
                nums[i] = left[p1] < right[p2] ? left[p1++] : right[p2++];
            } else if (p1 < left.length) {
                nums[i] = left[p1++];
            } else {
                nums[i] = right[p2++];
            }
        }
        return nums;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
