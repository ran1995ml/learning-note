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
        char[] ch = s.toCharArray();
        boolean[][] dp = new boolean[ch.length][ch.length];
        for (int i = ch.length - 1; i >= 0; i--) {
            for (int j = i; j < ch.length; j++) {
                if (ch[i] == ch[j] && (j - i <= 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    sum++;
                }
            }
        }
        return sum;
    }
}
