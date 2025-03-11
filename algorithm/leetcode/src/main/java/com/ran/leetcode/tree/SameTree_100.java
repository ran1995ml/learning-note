package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

/**
 * SameTree_100
 *
 * @author rwei
 * @since 2024/7/23 10:37
 */
public class SameTree_100 {
    public static void main(String[] args) {
        SameTree_100 obj = new SameTree_100();
        String data1 = "1,2,null,null,1,null,null";
        String data2 = "1,2,null,null,1,null,null";
        TreeNode p = SerializeBinaryTree_297.deserialize(data1);
        TreeNode q = SerializeBinaryTree_297.deserialize(data2);
        System.out.println(obj.isSameTree(p, q));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
