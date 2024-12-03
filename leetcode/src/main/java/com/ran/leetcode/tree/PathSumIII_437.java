package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * PathSumIII_437
 *
 * @author rwei
 * @since 2024/11/13 15:03
 */
public class PathSumIII_437 {
    private int sum;

    public static void main(String[] args) {
        PathSumIII_437 obj = new PathSumIII_437();
        String data = "10,5,3,3,null,null,-2,null,null,2,null,1,null,null,-3,null,11,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        int targetSum = 8;
        System.out.println(obj.pathSum(root, targetSum));
    }

    public int pathSum(TreeNode root, long targetSum) {
        if (root == null) return sum;
        dfs(root, targetSum);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return sum;
    }

    private void dfs(TreeNode root, long targetSum) {
        if (root == null) return;
        if (root.val == targetSum) sum++;
        dfs(root.left, targetSum - root.val);
        dfs(root.right, targetSum - root.val);
    }
}
