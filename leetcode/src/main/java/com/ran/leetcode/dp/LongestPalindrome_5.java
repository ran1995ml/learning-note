package com.ran.leetcode.dp;

/**
 * LongestPalindrome_5
 * s[i] == s[j]: dp[i][j] = dp[i+1][j-1]
 * s[i] != s[j]: dp[i][j] =
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
        if (s == null || s.length() == 0) {
            return s;
        }

        int max = 0;
        String str = "";
        char[] ch = s.toCharArray();
        boolean[][] dp = new boolean[ch.length][ch.length];


        for (int i=ch.length-1;i>=0;i--) {
            for (int j=i;j<ch.length;j++) {
                if (ch[i] == ch[j] && (j - i <= 2 || dp[i+1][j-1])) {
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
