package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;
import java.util.List;


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
        ListNode middle = getMiddle(head);
        ListNode p2 = middle.next;
        middle.next = null;
        return merge(sortList(head), sortList(p2));
    }

    public ListNode merge(ListNode p1, ListNode p2) {
        if (p1 == null && p2 == null) return null;
        if (p1 == null || p2 == null) return p1 == null ? p2 : p1;
        if (p1.val < p2.val) {
            p1.next = merge(p1.next, p2);
            return p1;
        } else {
            p2.next = merge(p1, p2.next);
            return p2;
        }
    }

    public ListNode getMiddle(ListNode head) {
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
