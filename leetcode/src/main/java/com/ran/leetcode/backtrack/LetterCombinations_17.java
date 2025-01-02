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
    public static void main(String[] args) {
        String digits = "23";
        LetterCombinations_17 obj = new LetterCombinations_17();
        System.out.println(obj.letterCombinations(digits));
    }

    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits.length() == 0) return ans;
        String[] digit2letter = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(ans, digit2letter, digits, new StringBuffer(), 0);
        return ans;
    }

    private void dfs(List<String> ans, String[] digit2letter, String digits, StringBuffer sb, int index) {
        if (index == digits.length()) {
            ans.add(new StringBuffer(sb).toString());
            return;
        }
        int num = digits.charAt(index) - '0';
        String str = digit2letter[num];
        for (char c : str.toCharArray()) {
            sb.append(c);
            dfs(ans, digit2letter, digits, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
