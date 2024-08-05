package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * BuildTreePreorderInorder_105
 *
 * @author rwei
 * @since 2024/7/25 10:33
 */
public class BuildTreePreorderInorder_105 {
    public static void main(String[] args) {
        BuildTreePreorderInorder_105 obj = new BuildTreePreorderInorder_105();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = obj.buildTree(preorder, inorder);
        System.out.println(SerializeBinaryTree_297.serialize(root));
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int s1, int e1, int[] inorder, int s2, int e2) {
        if (s1 > e1 || s2 > e2) return null;
        int value = preorder[s1];
        int index = s2;
        while (inorder[index] != value) {
            index++;
        }
        TreeNode root = new TreeNode(value);
        root.left = build(preorder, s1 + 1, s1 + index - s2, inorder, s2, index - 1);
        root.right = build(preorder, s1 + index - s2 + 1, e1, inorder, index + 1, e2);
        return root;
    }
}
