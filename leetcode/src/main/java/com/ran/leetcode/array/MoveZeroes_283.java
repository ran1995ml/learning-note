package com.ran.leetcode.array;

import java.util.Arrays;

/**
 * MoveZeroes_283
 *
 * @author rwei
 * @since 2024/10/3 15:42
 */
public class MoveZeroes_283 {
    public static void main(String[] args) {
        MoveZeroes_283 obj = new MoveZeroes_283();
        int[] nums = {0, 1, 0, 3, 12};
        obj.moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
    }

    public void moveZeroes(int[] nums) {
        int p = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[p++] = nums[i];
            }
        }
        while (p < nums.length) {
            nums[p++] = 0;
        }
    }
}
