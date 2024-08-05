package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * SerializeBinaryTree_297
 *
 * @author rwei
 * @since 2024/7/21 21:40
 */
public class SerializeBinaryTree_297 {
    public static void main(String[] args) {
        String data = "1,2,2,3,null,null,4,null,null,4,null,null,3,null,null,";
        TreeNode root = deserialize(data);
        String strs = serialize(root);
        System.out.println(strs);
    }

    public static String serialize(TreeNode root) {
        StringBuffer sb = new StringBuffer();
        serDfs(sb, root);
        return sb.toString();
    }

    private static void serDfs(StringBuffer sb, TreeNode root) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        serDfs(sb, root.left);
        serDfs(sb, root.right);
    }

    public static TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        AtomicInteger index = new AtomicInteger(0);
        return deserDfs(nodes, index);
    }

    private static TreeNode deserDfs(String[] nodes, AtomicInteger index) {
        int value = index.getAndIncrement();
        if (nodes[value].equals("null")) {
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.parseInt(nodes[value]));
            root.left = deserDfs(nodes, index);
            root.right = deserDfs(nodes, index);
            return root;
        }
    }
}
