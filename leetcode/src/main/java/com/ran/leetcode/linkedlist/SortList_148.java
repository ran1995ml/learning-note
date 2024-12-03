package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;


/**
 * SortList_148
 *
 * @author rwei
 * @since 2024/9/30 10:49
 */
public class SortList_148 {
    public static void main(String[] args) {
        SortList_148 obj = new SortList_148();
        int[] nums = {4, 2, 1, 3};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode node = obj.sortList(head);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(node)));
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode middleNode = getMiddleNode(head);
        ListNode p1 = head;
        ListNode p2 = middleNode.next;
        middleNode.next = null;
        return sort(sortList(p1), sortList(p2));
    }

    private ListNode sort(ListNode p1, ListNode p2) {
        ListNode node = new ListNode(-1);
        ListNode p = node;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        p.next = p1 == null ? p2 : p1;
        return node.next;
    }

    private ListNode getMiddleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode p1 = node;
        ListNode p2 = node;

        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
    }
}
