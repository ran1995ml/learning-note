package com.ran.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * LengthOfLongestSubstring_3
 * 滑动窗口
 *
 * @author rwei
 * @since 2024/5/13 11:15
 */
public class LengthOfLongestSubstring_3 {
    public static void main(String[] args) {
        String s = "pwwkew";
        LengthOfLongestSubstring_3 obj = new LengthOfLongestSubstring_3();
        System.out.println(obj.lengthOfLongestSubstring(s));
    }

    public int lengthOfLongestSubstring(String s) {
        char[] ch = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        int left = 0;
        int right = 0;
        while (right < ch.length) {
            char c1 = ch[right];
            map.put(c1, map.getOrDefault(c1, 0) + 1);
            while (map.get(c1) > 1) {
                char c2 = ch[left];
                map.put(c2, map.get(c2) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }
}
