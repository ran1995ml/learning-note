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
        int min = Integer.MAX_VALUE;
        char[] sh = s.toCharArray();
        char[] th = t.toCharArray();
        int[] needed = new int[128];
        int[] window = new int[128];
        int left = 0;
        int right = 0;
        int count = 0;
        int target = 0;

        for (char c : th) {
            needed[c - 'A']++;
        }

        for (int i : needed) {
            if (i > 0) target++;
        }

        while (right < sh.length) {
            char c1 = sh[right];
            if (needed[c1 - 'A'] > 0) {
                window[c1 - 'A']++;
                if (window[c1 - 'A'] == needed[c1 - 'A']) {
                    count++;
                }
            }
            while (count == target) {
                char c2 = sh[left];
                if (right - left + 1 < min) {
                    min = right - left + 1;
                    str = s.substring(left, right + 1);
                }
                if (needed[c2 - 'A'] > 0) {
                    window[c2 - 'A']--;
                    if (window[c2 - 'A'] < needed[c2 - 'A']) {
                        count--;
                    }
                }
                left++;
            }
            right++;
        }

        return str;
    }
}
