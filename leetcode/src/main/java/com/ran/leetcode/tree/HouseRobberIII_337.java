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
        int value1 = 0;
        int value2 = 0;
        if (root.left != null) {
            value1 += map.get(root.left);
            value2 += root.left.left == null ? 0 : map.get(root.left.left);
            value2 += root.left.right == null ? 0 : map.get(root.left.right);
        }
        if (root.right != null) {
            value1 += map.get(root.right);
            value2 += root.right.left == null ? 0 : map.get(root.right.left);
            value2 += root.right.right == null ? 0 : map.get(root.right.right);
        }
        map.put(root, Math.max(value1, value2 + root.val));
    }
}
