package com.ran.leetcode.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * Subsets_78
 *
 * @author rwei
 * @since 2024/6/28 15:47
 */
public class Subsets_78 {
    public static void main(String[] args) {
        Subsets_78 obj = new Subsets_78();
        int[] nums = {1, 2, 3};
        System.out.println(obj.subsets(nums));
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(ans, new ArrayList<>(), nums, 0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, List<Integer> list, int[] nums, int index) {
        if (list.size() <= nums.length) {
            ans.add(new ArrayList<>(list));
        }

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            dfs(ans, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
