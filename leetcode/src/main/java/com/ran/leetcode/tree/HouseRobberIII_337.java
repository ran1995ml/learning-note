package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * HouseRobberIII_337
 *
 * @author rwei
 * @since 2024/11/11 14:52
 */
public class HouseRobberIII_337 {

    public static void main(String[] args) {
        String data = "3,4,1,null,null,3,null,null,5,null,1,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        HouseRobberIII_337 obj = new HouseRobberIII_337();
        System.out.println(obj.rob(root));
    }

    public int rob(TreeNode root) {
        if (root == null) return 0;
        Map<TreeNode, Integer> map = new HashMap<>();
        dfs(root, map);
        return map.get(root);
    }

    private void dfs(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return;
        dfs(root.left, map);
        dfs(root.right, map);
        int v1 = 0;
        int v2 = 0;
        if (root.left != null) {
            v1 += map.get(root.left);
            v2 += root.left.left == null ? 0 : map.get(root.left.left);
            v2 += root.left.right == null ? 0 : map.get(root.left.right);
        }
        if (root.right != null) {
            v1 += map.get(root.right);
            v2 += root.right.left == null ? 0 : map.get(root.right.left);
            v2 += root.right.right == null ? 0 : map.get(root.right.right);
        }
        map.put(root, Math.max(root.val + v2, v1));
    }
}
