package com.ran.leetcode.slidingwindow;

import java.util.*;

/**
 * FindAnagrams_438
 *
 * @author rwei
 * @since 2024/11/13 15:28
 */
public class FindAnagrams_438 {
    public static void main(String[] args) {
        FindAnagrams_438 obj = new FindAnagrams_438();
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(obj.findAnagrams(s, p));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        char[] sh = s.toCharArray();
        char[] ph = p.toCharArray();
        int[] needs = new int[128];
        int[] windows = new int[128];
        for (char c : ph) {
            needs[c - 'a']++;
        }

        int left = 0;
        int right = 0;
        while (right < sh.length) {
            char c1 = sh[right];
            if (needs[c1 - 'a'] > 0) windows[c1 - 'a']++;
            if (right - left + 1 == ph.length) {
                if (Arrays.equals(needs, windows)) {
                    ans.add(left);
                }
                char c2 = sh[left];
                if (needs[c2 - 'a'] > 0) {
                    windows[c2 - 'a']--;
                }
                left++;
            }
            right++;
        }

        return ans;
    }
}
