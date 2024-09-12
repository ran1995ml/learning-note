package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * InvertBinaryTree_226
 *
 * @author rwei
 * @since 2024/9/9 13:14
 */
public class InvertBinaryTree_226 {
    public static void main(String[] args) {
        InvertBinaryTree_226 obj = new InvertBinaryTree_226();
        String data = "4,2,1,null,null,3,null,null,7,6,null,null,9,null,null,";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        TreeNode node = obj.invertTree(root);
        System.out.println(SerializeBinaryTree_297.serialize(node));
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = invertTree(root.left);
        root.left = invertTree(root.right);
        root.right = temp;
        return root;
    }
}
