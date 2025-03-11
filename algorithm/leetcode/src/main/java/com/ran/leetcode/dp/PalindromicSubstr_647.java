package com.ran.leetcode.dp;

/**
 * PalindromicSubstr_647
 *
 * @author rwei
 * @since 2024/10/6 22:38
 */
public class PalindromicSubstr_647 {
    public static void main(String[] args) {
        PalindromicSubstr_647 obj = new PalindromicSubstr_647();
        String s = "abc";
        System.out.println(obj.countSubstrings(s));
    }

    public int countSubstrings(String s) {
        int sum = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    sum++;
                }
            }
        }
        return sum;
    }
}
