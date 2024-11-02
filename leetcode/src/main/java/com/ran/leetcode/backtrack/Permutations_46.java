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
        int[] nums = {1, 2, 3};
        System.out.println(obj.permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        boolean[] visited = new boolean[nums.length];
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), visited, nums);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, boolean[] visited, int[] nums) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            list.add(nums[i]);
            dfs(ans, list, visited, nums);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}
