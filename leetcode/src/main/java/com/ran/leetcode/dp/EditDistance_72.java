package com.ran.leetcode.dp;

/**
 * EditDistance_72
 *
 * @author rwei
 * @since 2024/6/12 14:55
 */
public class EditDistance_72 {
    public static void main(String[] args) {
        EditDistance_72 obj = new EditDistance_72();
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(obj.minDistance(word1, word2));
    }

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 1; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
