package com.ran.leetcode.entity;

/**
 * TreeNode
 *
 * @author rwei
 * @since 2024/7/19 14:56
 */
public class TreeNode {
    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
