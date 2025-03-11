package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * LowestCommonAncestor_236
 *
 * @author rwei
 * @since 2024/9/23 13:30
 */
public class LowestCommonAncestor_236 {
    public static void main(String[] args) {
        LowestCommonAncestor_236 obj = new LowestCommonAncestor_236();
        String data = "3,5,6,null,null,2,7,null,null,4,null,null,1,0,null,null,8,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        TreeNode p = root.left;
        TreeNode q = root.right;
        TreeNode node = obj.lowestCommonAncestor(root, p, q);
        System.out.println(node.val);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) return null;
        if (left == null || right == null) return left == null ? right : left;
        return root;
    }
}
