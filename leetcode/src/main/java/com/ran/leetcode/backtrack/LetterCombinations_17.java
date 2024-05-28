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
        String digits = "2";
        LetterCombinations_17 obj = new LetterCombinations_17();
        System.out.println(obj.letterCombinations(digits));
    }

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        dfs(res, digits, new StringBuffer(), 0);
        return res;
    }

    private void dfs(List<String> res, String digits, StringBuffer sb, int index) {
        if (sb.length() == digits.length()) {
            res.add(new String(sb));
            return;
        }

        int number = digits.charAt(index) - '0';
        char[] letters = digit2letter[number].toCharArray();
        for (char letter : letters) {
            sb.append(letter);
            dfs(res, digits, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
