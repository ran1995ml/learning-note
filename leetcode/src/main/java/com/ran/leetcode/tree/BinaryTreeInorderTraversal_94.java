package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * BinaryTreeInorderTraversal_94
 *
 * @author rwei
 * @since 2024/7/23 09:59
 */
public class BinaryTreeInorderTraversal_94 {
    public static void main(String[] args) {
        BinaryTreeInorderTraversal_94 obj = new BinaryTreeInorderTraversal_94();
        String data = "1,null,2,3,null,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.inorderTraversal(root));
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                ans.add(root.val);
                root = root.right;
            }
        }
        return ans;
    }
}
