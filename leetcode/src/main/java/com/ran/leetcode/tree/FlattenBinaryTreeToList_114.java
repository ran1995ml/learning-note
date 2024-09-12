package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * FlattenBinaryTreeToList_114
 *
 * @author rwei
 * @since 2024/8/9 11:20
 */
public class FlattenBinaryTreeToList_114 {
    public static void main(String[] args) {
        FlattenBinaryTreeToList_114 obj = new FlattenBinaryTreeToList_114();
        String str = "1,2,3,null,null,4,null,null,5,null,6,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(str);
//        obj.flatten(root);
        obj.dfs(root);
        System.out.println(SerializeBinaryTree_297.serialize(root));
    }

    public void flatten(TreeNode root) {
        while (root != null) {
            TreeNode left = root.left;
            TreeNode right = root.right;
            if (left != null) {
                root.right = left;
                while (left.right != null) {
                    left = left.right;
                }
                left.right = right;
                root.left = null;
            }
            root = root.right;
        }
    }

    public void dfs(TreeNode root) {
        if (root == null) return;
        TreeNode right = root.right;
        TreeNode left = root.left;
        if (left != null) {
            root.right = left;
            while (left.right != null) {
                left = left.right;
            }
            left.right = right;
            root.left = null;
        }
        dfs(root.left);
        dfs(root.right);
    }
}