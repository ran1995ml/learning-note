package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * MaxDepthBinaryTree_104
 *
 * @author rwei
 * @since 2024/7/25 10:29
 */
public class MaxDepthBinaryTree_104 {
    public static void main(String[] args) {
        MaxDepthBinaryTree_104 obj = new MaxDepthBinaryTree_104();
        String data = "3,9,null,null,20,15,null,null,7,null,null,";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }
}
