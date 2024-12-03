package com.ran.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Combinations_77
 *
 * @author rwei
 * @since 2024/6/28 15:40
 */
public class Combinations_77 {
    public static void main(String[] args) {
        Combinations_77 obj = new Combinations_77();
        int n = 4;
        int k = 2;
        System.out.println(obj.combine(n, k));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), n, k, 1);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, int n, int k, int index) {
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(ans, list, n, k, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
