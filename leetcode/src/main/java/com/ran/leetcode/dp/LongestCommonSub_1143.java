package com.ran.leetcode.dp;

/**
 * LongestCommonSub_1143
 *
 * @author rwei
 * @since 2024/12/24 09:49
 */
public class LongestCommonSub_1143 {
    public static void main(String[] args) {
        LongestCommonSub_1143 obj = new LongestCommonSub_1143();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(obj.longestCommonSubsequence(text1, text2));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], Math.max(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
