package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * MergeTrees_617
 *
 * @author rwei
 * @since 2024/10/6 15:57
 */
public class MergeTrees_617 {
    public static void main(String[] args) {
        MergeTrees_617 obj = new MergeTrees_617();
        String data1 = "1,3,5,null,null,null,2,null,null";
        String data2 = "2,1,null,4,null,null,3,null,7,null,null";
        TreeNode root1 = SerializeBinaryTree_297.deserialize(data1);
        TreeNode root2 = SerializeBinaryTree_297.deserialize(data2);
        TreeNode node = obj.mergeTrees(root1, root2);
        System.out.println(SerializeBinaryTree_297.serialize(node));
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        if (root1 == null || root2 == null) return root1 == null ? root2 : root1;
        TreeNode root = new TreeNode(root1.val + root2.val);
        root.left = mergeTrees(root1.left, root2.left);
        root.right = mergeTrees(root1.right, root2.right);
        return root;
    }
}
