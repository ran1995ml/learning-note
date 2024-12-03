package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

/**
 * LinkedListCycle_141
 *
 * @author rwei
 * @since 2024/8/12 11:23
 */
public class LinkedListCycle_141 {
    public static void main(String[] args) {
        LinkedListCycle_141 obj = new LinkedListCycle_141();
        int[] nums = {3, 2, 0, -4};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode node = ListNode.findNthNode(head, 4);
        node.next = head;
        System.out.println(obj.hasCycle(head));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode p1 = head;
        ListNode p2 = head;

        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) return true;
        }
        return false;
    }
}
