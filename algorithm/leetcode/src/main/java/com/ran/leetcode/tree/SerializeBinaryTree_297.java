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
        serializeDfs(root, sb);
        return sb.toString();
    }

    private static void serializeDfs(TreeNode root, StringBuffer sb) {
        if (root == null){
            sb.append("null");
            sb.append(",");
        } else {
            sb.append(root.val);
            sb.append(",");
            serializeDfs(root.left, sb);
            serializeDfs(root.right, sb);
        }
    }

    public static TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        AtomicInteger index = new AtomicInteger(0);
        return deserializeDfs(nodes, index);
    }

    private static TreeNode deserializeDfs(String[] nodes, AtomicInteger index) {
        String value = nodes[index.getAndIncrement()];
        if (value.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = deserializeDfs(nodes, index);
        root.right = deserializeDfs(nodes, index);
        return root;
    }
}
