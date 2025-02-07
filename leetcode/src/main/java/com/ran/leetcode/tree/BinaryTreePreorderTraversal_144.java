package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * BinaryTreePreorderTraversal_144
 *
 * @author rwei
 * @since 2024/7/26 10:40
 */
public class BinaryTreePreorderTraversal_144 {
    public static void main(String[] args) {
        BinaryTreePreorderTraversal_144 obj = new BinaryTreePreorderTraversal_144();
        String data = "1,null,2,3,null,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.preorderTraversal(root));
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ans.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return ans;
    }
}
