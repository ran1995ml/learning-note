package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * BinaryTreeMaxPathSum_124
 *
 * @author rwei
 * @since 2024/8/11 15:04
 */
public class BinaryTreeMaxPathSum_124 {
    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        BinaryTreeMaxPathSum_124 obj = new BinaryTreeMaxPathSum_124();
        String data = "-10,9,null,null,20,15,null,null,7,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.maxPathSum(root));
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        max = Math.max(max, left + right + root.val);
        return Math.max(left, right) + root.val;
    }
}
