package com.ran.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * MinWindowSubstr_76
 *
 * @author rwei
 * @since 2024/6/28 14:55
 */
public class MinWindowSubstr_76 {
    public static void main(String[] args) {
        MinWindowSubstr_76 obj = new MinWindowSubstr_76();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(obj.minWindow(s, t));
    }

    public String minWindow(String s, String t) {
        String str = "";
        int min = Integer.MAX_VALUE;
        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        char[] th = t.toCharArray();
        char[] sh = s.toCharArray();
        for (char c : th) {
            needs.put(c, needs.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int count = 0;
        while (right < sh.length) {
            char c1 = sh[right];
            if (needs.containsKey(c1)) {
                windows.put(c1, windows.getOrDefault(c1, 0) + 1);
                if (windows.get(c1).equals(needs.get(c1))) count++;
            }
            while (count == needs.size()) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    str = s.substring(left, right + 1);
                }
                char c2 = sh[left];
                if (needs.containsKey(c2)) {
                    windows.put(c2, windows.get(c2) - 1);
                    if (windows.get(c2) < needs.get(c2)) count--;
                }
                left++;
            }
            right++;
        }
        return str;
    }
}
