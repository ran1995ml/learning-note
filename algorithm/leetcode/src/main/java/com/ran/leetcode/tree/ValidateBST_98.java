package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * ValidateBST_98
 *
 * @author rwei
 * @since 2024/7/23 22:52
 */
public class ValidateBST_98 {
    private long max = Long.MIN_VALUE;

    private boolean isValid = true;

    public static void main(String[] args) {
        ValidateBST_98 obj = new ValidateBST_98();
        String data = "5,1,null,null,4,3,null,null,6,null,null,";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.isValidBST(root));
    }

    public boolean isValidBST(TreeNode root) {
        dfs(root);
        return isValid;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (root.val > max) {
            max = root.val;
        } else {
            isValid = false;
            return;
        }
        dfs(root.right);
    }
}
