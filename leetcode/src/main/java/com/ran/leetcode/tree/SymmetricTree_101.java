package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * SymmetricTree_101
 *
 * @author rwei
 * @since 2024/7/24 16:49
 */
public class SymmetricTree_101 {
    public static void main(String[] args) {
        SymmetricTree_101 obj = new SymmetricTree_101();
        String data1 = "1,2,3,null,null,4,null,null,2,4,null,null,3,null,null,";
        TreeNode root = SerializeBinaryTree_297.deserialize(data1);
        System.out.println(obj.isSymmetric(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode root1, TreeNode roo2) {
        if (root1 == null && roo2 == null) return true;
        if (root1 == null || roo2 == null) return false;
        if (root1.val != roo2.val) return false;
        return dfs(root1.left, roo2.right) && dfs(root1.right, roo2.left);
    }
}
