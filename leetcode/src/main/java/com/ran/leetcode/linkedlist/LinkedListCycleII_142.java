package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

/**
 * LinkedListCycleII_142
 * 2(l1 + l2) = l1 + l2 + r
 * l1 + l2 = r
 * l2 = r - l1
 *
 * @author rwei
 * @since 2024/8/12 13:42
 */
public class LinkedListCycleII_142 {
    public static void main(String[] args) {
        LinkedListCycleII_142 obj = new LinkedListCycleII_142();
        int[] nums = {3, 2, 0, -4};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode node4 = ListNode.findNthNode(head, 4);
        ListNode node2 = ListNode.findNthNode(head, 2);
        node4.next = node2;
        ListNode node = obj.detectCycle(head);
        System.out.println(node.val);
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) break;
        }
        if (p1 == null || p1.next == null) return null;
        p1 = head;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }
}
