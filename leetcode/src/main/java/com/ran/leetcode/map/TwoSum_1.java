package com.ran.leetcode.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * TwoSum_1
 * 用Map降低复杂度
 *
 * @author rwei
 * @since 2024/5/11 14:17
 */
public class TwoSum_1 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        TwoSum_1 obj = new TwoSum_1();
        System.out.println(Arrays.toString(obj.twoSum(nums, target)));
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
