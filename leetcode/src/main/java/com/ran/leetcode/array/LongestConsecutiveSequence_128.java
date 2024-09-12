package com.ran.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * LongestConsecutiveSequence_128
 *
 * @author rwei
 * @since 2024/8/11 15:16
 */
public class LongestConsecutiveSequence_128 {
    public static void main(String[] args) {
        LongestConsecutiveSequence_128 obj = new LongestConsecutiveSequence_128();
        int[] nums = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println(obj.longestConsecutive(nums));
    }

    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max = 0;
        for (int num : set) {
            int len = 1;
            if (!set.contains(num - 1)) {
                int cur = num + 1;
                while (set.contains(cur)) {
                    cur++;
                    len++;
                }
            }
            max = Math.max(max, len);
        }

        return max;
    }
}
