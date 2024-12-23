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

    private void dfs(List<String> ans, StringBuffer sb, int n1, int n2, int n) {
        if (n2 > n1) return;
        if (n1 > n) return;
        if (n1 == n && n2 == n){
            ans.add(String.valueOf(sb));
            return;
        }

        sb.append('(');
        dfs(ans, sb, n1 + 1, n2, n);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(')');
        dfs(ans, sb, n1, n2 + 1, n);
        sb.deleteCharAt(sb.length() - 1);
    }
}
