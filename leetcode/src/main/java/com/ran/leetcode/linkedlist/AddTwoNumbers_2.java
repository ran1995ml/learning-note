package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * AddTwoNumbers_2
 *
 * @author rwei
 * @since 2024/5/11 14:25
 */
public class AddTwoNumbers_2 {
    public static void main(String[] args) {
        int[] nums1 = {9, 9, 9, 9, 9, 9, 9};
        int[] nums2 = {9, 9, 9, 9};
        AddTwoNumbers_2 obj = new AddTwoNumbers_2();
        ListNode l1 = ListNode.convertArray2LinkedList(nums1);
        ListNode l2 = ListNode.convertArray2LinkedList(nums2);
        ListNode listNode = obj.addTwoNumbers(l1, l2);
        int[] nums = ListNode.convertLinkedList2Array(listNode);
        System.out.println(Arrays.toString(nums));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode node = new ListNode(-1);
        ListNode p = node;
        int col = 0;

        while (p1 != null || p2 != null) {
            int value1 = p1 == null ? 0 : p1.val;
            int value2 = p2 == null ? 0 : p2.val;
            int value = (value1 + value2 + col) % 10;
            col = (value1 + value2 + col) / 10;
            p.next = new ListNode(value);
            p = p.next;
            p1 = p1 == null ? null : p1.next;
            p2 = p2 == null ? null : p2.next;
        }

        if (col > 0) {
            p.next = new ListNode(col);
        }
        return node.next;
    }
}
