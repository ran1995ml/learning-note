package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * BinaryTreeZigzagLevelTraversal_103
 *
 * @author rwei
 * @since 2024/7/25 10:19
 */
public class BinaryTreeZigzagLevelTraversal_103 {
    public static void main(String[] args) {
        BinaryTreeZigzagLevelTraversal_103 obj = new BinaryTreeZigzagLevelTraversal_103();
        String data = "3,9,null,null,20,15,null,null,7,null,null,";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.zigzagLevelOrder(root));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        int index = 0;
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (index % 2 == 0) {
                    list.addLast(node.val);
                } else {
                    list.addFirst(node.val);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            index++;
            ans.add(list);
        }
        return ans;
    }
}
