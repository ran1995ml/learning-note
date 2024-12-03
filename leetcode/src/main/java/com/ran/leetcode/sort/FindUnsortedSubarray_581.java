package com.ran.leetcode.sort;

import java.util.Arrays;

/**
 * FindUnsortedSubarray_581
 * 数组分成三段，首尾两段是升序的。
 * 中段最小值大于首段最大值，中段最大值小于尾段最小值
 * 从左到右，最大值max进入尾段前都小于max
 * 从右到左，最小值min进入首段前都大于min
 * @author rwei
 * @since 2024/11/19 16:35
 */
public class FindUnsortedSubarray_581 {
    public static void main(String[] args) {
        FindUnsortedSubarray_581 obj = new FindUnsortedSubarray_581();
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(obj.findUnsortedSubarray1(nums));
    }

    public int findUnsortedSubarray(int[] nums) {
        int[] sortedNums = new int[nums.length];
        System.arraycopy(nums, 0, sortedNums, 0, nums.length);
        Arrays.sort(sortedNums);
        int left = 0;
        int right = nums.length - 1;
        while (left < nums.length && nums[left] == sortedNums[left]) left++;
        while (right >= 0 && nums[right] == sortedNums[right]) right--;
        if (right <= left) return 0;
        return right - left + 1;
    }

    public int findUnsortedSubarray1(int[] nums) {
        int max = nums[0];
        int end = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max) {
                max = nums[i];
            } else {
                end = i;
            }
        }

        int min = nums[nums.length - 1];
        int start = nums.length;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] <= min) {
                min = nums[i];
            } else {
                start = i;
            }
        }
        return end - start + 1;
    }
}
