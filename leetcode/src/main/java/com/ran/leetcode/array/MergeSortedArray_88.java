package com.ran.leetcode.array;

import java.util.Arrays;

/**
 * MergeSortedArray
 *
 * @author rwei
 * @since 2024/7/4 17:04
 */
public class MergeSortedArray_88 {
    public static void main(String[] args) {
        MergeSortedArray_88 obj = new MergeSortedArray_88();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        System.out.println(Arrays.toString(nums1));
        obj.merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (p1 >= 0 && p2 >= 0) {
                nums1[i] = nums1[p1] > nums2[p2] ? nums1[p1--] : nums2[p2--];
            } else if (p1 >= 0 || p2 >= 0) {
                nums1[i] = p1 >= 0? nums1[p1--] : nums2[p2--];
            }
        }
    }
}
