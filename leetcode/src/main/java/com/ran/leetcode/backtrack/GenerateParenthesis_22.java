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
        dfs(ans, new StringBuffer(), n, 0, 0);
        return ans;
    }

    private void dfs(List<String> ans, StringBuffer sb, int n, int n1, int n2) {
        if (n1 == n && n2 == n) {
            ans.add(new StringBuffer(sb).toString());
            return;
        }
        if (n1 < n2) return;
        if (n1 > n) return;
        sb.append('(');
        dfs(ans, sb, n, n1 + 1, n2);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        dfs(ans, sb, n, n1, n2 + 1);
        sb.deleteCharAt(sb.length() - 1);
    }
}
