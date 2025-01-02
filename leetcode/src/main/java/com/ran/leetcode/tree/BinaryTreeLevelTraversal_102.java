package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BinaryTreeLevelTraversal_102
 *
 * @author rwei
 * @since 2024/7/25 10:10
 */
public class BinaryTreeLevelTraversal_102 {
    public static void main(String[] args) {
        BinaryTreeLevelTraversal_102 obj = new BinaryTreeLevelTraversal_102();
        String data = "3,9,null,null,20,15,null,null,7,null,null,";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.levelOrder(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            ans.add(list);
        }
        return ans;
    }
}
