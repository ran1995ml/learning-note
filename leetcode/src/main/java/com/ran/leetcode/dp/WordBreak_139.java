package com.ran.leetcode.dp;

import java.util.Arrays;
import java.util.List;

/**
 * WordBreak_139
 *
 * @author rwei
 * @since 2024/8/12 10:05
 */
public class WordBreak_139 {
    public static void main(String[] args) {
        WordBreak_139 obj = new WordBreak_139();
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(obj.wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length(); j > i; j--) {
                if (dp[i] && wordDict.contains(s.substring(i, j))) {
                    dp[j] = true;
                }
                if (dp[s.length()]) return true;
            }
        }
        return dp[s.length()];
    }
}
