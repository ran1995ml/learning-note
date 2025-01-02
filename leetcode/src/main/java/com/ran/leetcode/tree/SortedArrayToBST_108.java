package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * SortedArrayToBST_108
 *
 * @author rwei
 * @since 2024/12/20 13:50
 */
public class SortedArrayToBST_108 {
    public static void main(String[] args) {
        SortedArrayToBST_108 obj = new SortedArrayToBST_108();
        int[] nums = {-10, -3, 0, 5, 9};
        System.out.println(obj.sortedArrayToBST(nums).val);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }

    private TreeNode dfs(int[] nums, int start, int end) {
        if (start > end) return null;
        int index = (start + end) / 2;
        TreeNode root = new TreeNode(nums[index]);
        root.left = dfs(nums, start, index - 1);
        root.right = dfs(nums, index + 1, end);
        return root;
    }
}
