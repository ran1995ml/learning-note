package com.ran.leetcode.backtrack;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

/**
 * LetterCombinations_17
 *
 * @author rwei
 * @since 2024/5/14 15:12
 */
public class LetterCombinations_17 {
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations_17 obj = new LetterCombinations_17();
        System.out.println(obj.letterCombinations(digits));
    }

    public List<String> letterCombinations(String digits) {
        String[] digits2Letters = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        dfs(ans, digits2Letters, digits, new StringBuffer(), 0);
        return ans;
    }

    private void dfs(List<String> ans, String[] digits2Letters, String digits, StringBuffer sb, int index) {
        if (sb.length() == digits.length()) {
            ans.add(new StringBuffer(sb).toString());
            return;
        }
        int digit = digits.charAt(index) - '0';
        String letters = digits2Letters[digit];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            dfs(ans, digits2Letters, digits, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
