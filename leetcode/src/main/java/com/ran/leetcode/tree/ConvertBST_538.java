package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * ConvertBST_538
 *
 * @author rwei
 * @since 2024/10/27 19:39
 */
public class ConvertBST_538 {
    private int sum;

    public static void main(String[] args) {
        String data = "4,1,0,null,null,2,null,3,null,null,6,5,null,null,7,null,8,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        ConvertBST_538 obj = new ConvertBST_538();
        System.out.println(obj.convertBST(root));
    }

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        root.val = root.val + sum;
        sum = root.val;
        dfs(root.left);
    }
}
