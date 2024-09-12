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
        if (root == null) return max;
        dfs(root);
        maxPathSum(root.left);
        maxPathSum(root.right);
        return max;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        max = Math.max(max, root.val + left + right);
        return Math.max(root.val + left, root.val + right);
    }
}
