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
        int n = 1;
        int k = 1;
        System.out.println(obj.combine(n, k));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(1, n, k, new ArrayList<>(), ans);
        return ans;
    }

    private void dfs(int index, int n, int k, List<Integer> list, List<List<Integer>> ans) {
        if (list.size() == k) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = index; i <= n; i++) {
            list.add(i);
            dfs(i + 1, n, k, list, ans);
            list.remove(list.size() - 1);
        }
    }
}
