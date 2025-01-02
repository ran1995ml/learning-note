package com.ran.leetcode.slidingwindow;

import java.util.HashMap;
import java.util.Map;

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
        char[] sh = s.toCharArray();
        char[] th = t.toCharArray();
        int[] windows = new int[128];
        int[] needs = new int[128];
        int target = 0;
        for (char c : th) {
            if (needs[c - 'A'] == 0) target++;
            needs[c - 'A']++;
        }

        int left = 0;
        int right = 0;
        int count = 0;
        int min = sh.length + 1;
        while (right < sh.length) {
            char c1 = sh[right];
            if (needs[c1 - 'A'] > 0) {
                windows[c1 - 'A']++;
                if (windows[c1 - 'A'] == needs[c1 - 'A']) count++;
            }
            while (count == target) {
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    str = s.substring(left, right + 1);
                }
                char c2 = sh[left];
                if (needs[c2 - 'A'] > 0) {
                    windows[c2 - 'A']--;
                    if (windows[c2 - 'A'] < needs[c2 - 'A']) count--;
                }
                left++;
            }
            right++;
        }
        return str;
    }
}
