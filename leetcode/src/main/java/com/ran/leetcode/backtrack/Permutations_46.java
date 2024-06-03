package com.ran.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Permutations_46
 *
 * @author rwei
 * @since 2024/5/30 13:47
 */
public class Permutations_46 {
    public static void main(String[] args) {
        Permutations_46 obj = new Permutations_46();
        int[] nums = {0, 1};
        System.out.println(obj.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        dfs(ans, new ArrayList<>(), nums, visited);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums, boolean[] visited) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            list.add(nums[i]);
            dfs(ans, list, nums, visited);
            visited[i] = false;
            list.remove(list.size() - 1);
        }
    }
}
