package com.ran.leetcode.dp;

/**
 * LongestPalindrome_5
 * s[i] == s[j]: dp[i][j] = dp[i+1][j-1]
 *
 * @author rwei
 * @since 2024/5/13 17:51
 */
public class LongestPalindrome_5 {
    public static void main(String[] args) {
        String s = "cbbd";
        LongestPalindrome_5 obj = new LongestPalindrome_5();
        System.out.println(obj.longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        String str = "";
        int max = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i + 1 <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                        str = s.substring(i, j + 1);
                    }
                }
            }
        }
        return str;
    }
}
