package com.ran.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * LetterCombinations_17
 *
 * @author rwei
 * @since 2024/5/14 15:12
 */
public class LetterCombinations_17 {
    private final String[] digit2letter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations_17 obj = new LetterCombinations_17();
        System.out.println(obj.letterCombinations(digits));
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;
        dfs(ans, new StringBuffer(), digits, 0);
        return ans;
    }

    private void dfs(List<String> ans, StringBuffer sb, String digits, int index) {
        if (sb.length() == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        char[] letters = digit2letter[digits.charAt(index) - '0'].toCharArray();
        for (char letter : letters) {
            sb.append(letter);
            dfs(ans, sb, digits, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
