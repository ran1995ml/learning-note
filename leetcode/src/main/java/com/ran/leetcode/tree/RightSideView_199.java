package com.ran.leetcode.tree;

import com.ran.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * RightSideView_199
 *
 * @author rwei
 * @since 2024/12/23 10:05
 */
public class RightSideView_199 {
    public static void main(String[] args) {
        RightSideView_199 obj = new RightSideView_199();
        String data = "1,2,null,5,null,null,3,null,4,null,null";
        TreeNode root = SerializeBinaryTree_297.deserialize(data);
        System.out.println(obj.rightSideView(root));
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
                if (i == 0) ans.add(node.val);
            }
        }
        return ans;
    }
}
