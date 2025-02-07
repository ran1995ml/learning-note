package com.ran.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * SubarraySum_560
 * 前缀和
 * pre[i] = pre[i-1] + num[i]
 * [j..i]
 * pre[i] - pre[j-1] = k
 * @author rwei
 * @since 2024/11/18 15:08
 */
public class SubarraySum_560 {
    public static void main(String[] args) {
        SubarraySum_560 obj = new SubarraySum_560();
        int[] nums = {1, 2, 3};
        int k = 3;
        System.out.println(obj.subarraySum(nums, k));
    }

    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
