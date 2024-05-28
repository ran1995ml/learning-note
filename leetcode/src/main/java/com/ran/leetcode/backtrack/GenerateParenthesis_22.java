package com.ran.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * GenerateParenthesis_22
 *
 * @author rwei
 * @since 2024/5/20 10:37
 */
public class GenerateParenthesis_22 {
    public static void main(String[] args) {
        int n = 3;
        GenerateParenthesis_22 obj = new GenerateParenthesis_22();
        List<String> ans = obj.generateParenthesis(n);
        System.out.println(ans);
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(ans, new StringBuffer(), 0, 0, n);
        return ans;
    }

    private void dfs(List<String> ans, StringBuffer sb, int left, int right, int n) {
        if (left == n && right == n) {
            ans.add(String.valueOf(sb));
            return;
        }
        if (left > n | right > n) return;
        if (left < right) return;

        sb.append('(');
        dfs(ans, sb, left + 1, right, n);
        sb.deleteCharAt(sb.length() - 1);

        sb.append(')');
        dfs(ans, sb, left, right + 1, n);
        sb.deleteCharAt(sb.length() - 1);
    }
}
