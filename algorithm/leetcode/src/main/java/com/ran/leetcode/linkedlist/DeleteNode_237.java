package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

/**
 * DeleteNode_237
 *
 * @author rwei
 * @since 2024/9/9 11:14
 */
public class DeleteNode_237 {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
