package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * KthSmallestBST_230
 *
 * @author rwei
 * @since 2024/12/22 16:41
 */
public class KthSmallestBST_230 {
    private int index = 0;

    private int ans = 0;

    public static void main(String[] args) {
        KthSmallestBST_230 obj = new KthSmallestBST_230();
        String data = "3,1,null,2,null,null,4,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        int k = 1;
        System.out.println(obj.kthSmallest(root, k));
    }

    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return ans;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) return;
        dfs(root.left, k);
        index++;
        if (index == k) {
            ans = root.val;
            return;
        }
        dfs(root.right, k);
    }
}
