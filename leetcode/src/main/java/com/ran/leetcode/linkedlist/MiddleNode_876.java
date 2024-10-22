package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

/**
 * MiddleNode_876
 *
 * @author rwei
 * @since 2024/10/3 17:15
 */
public class MiddleNode_876 {
    public static void main(String[] args) {
        MiddleNode_876 obj = new MiddleNode_876();
        int[] nums = {1, 2, 4, 5};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode node = obj.middleNode(head);
        System.out.println(node.val);
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
    }
}
