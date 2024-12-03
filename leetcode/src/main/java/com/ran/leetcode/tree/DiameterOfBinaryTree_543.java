package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * DiameterOfBinaryTree_543
 *
 * @author rwei
 * @since 2024/10/3 17:20
 */
public class DiameterOfBinaryTree_543 {
    private int max;

    public static void main(String[] args) {
        DiameterOfBinaryTree_543 obj = new DiameterOfBinaryTree_543();
        String data = "1,2,4,null,null,5,null,null,3,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.diameterOfBinaryTree(root));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        int right = dfs(root.right);
        max = Math.max(max, left + right);
        return Math.max(left, right) + 1;
    }
}
