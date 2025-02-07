package com.ran.leetcode.slidingwindow;

import java.util.Arrays;

/**
 * PermutationInString_567
 *
 * @author rwei
 * @since 2024/11/18 15:56
 */
public class PermutationInString_567 {
    public static void main(String[] args) {
        PermutationInString_567 obj = new PermutationInString_567();
        String s1 = "ba";
        String s2 = "eidbaooo";
        System.out.println(obj.checkInclusion(s1, s2));
    }

    public boolean checkInclusion(String s1, String s2) {
        char[] sh1 = s1.toCharArray();
        char[] sh2 = s2.toCharArray();
        int[] needs = new int[128];
        int[] windows = new int[128];
        for (char c : sh1) needs[c - 'a']++;

        int left = 0;
        int right = 0;
        while (right < sh2.length) {
            char c1 = sh2[right];
            if (needs[c1 - 'a'] > 0) windows[c1 - 'a']++;
            if (right - left + 1 == sh1.length) {
                if (Arrays.equals(needs, windows)) return true;
                char c2 = sh2[left];
                if (needs[c2 - 'a'] > 0) windows[c2 - 'a']--;
                left++;
            }
            right++;
        }
        return false;
    }
}
