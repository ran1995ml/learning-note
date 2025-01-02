package com.ran.leetcode.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * WordBreak_139
 *
 * @author rwei
 * @since 2024/8/12 10:05
 */
public class WordBreak_139 {
    public static void main(String[] args) {
        WordBreak_139 obj = new WordBreak_139();
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        System.out.println(obj.wordBreak(s, wordDict));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 0; i <= s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                if (dp[i] && wordDict.contains(s.substring(i, j))) {
                    dp[j] = true;
                    if (dp[s.length()]) return true;
                }
            }
        }
        return dp[s.length()];
    }
}
