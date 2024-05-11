package com.ran.leetcode.array;

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
        int[] nums1 = {9,9,9,9,9,9,9};
        int[] nums2 = {9,9,9,9};
        AddTwoNumbers_2 obj = new AddTwoNumbers_2();
        ListNode l1 = ListNode.convertArray2LinkedList(nums1);
        ListNode l2 = ListNode.convertArray2LinkedList(nums2);
        ListNode listNode = obj.addTwoNumbers(l1, l2);
        int[] nums = ListNode.convertLinkedList2Array(listNode);
        System.out.println(Arrays.toString(nums));
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node = new ListNode(-1);
        ListNode head = node;
        ListNode head1 = l1;
        ListNode head2 = l2;
        int col = 0;

        while (head1 != null || head2 != null) {
            int val1 = head1 == null ? 0 : head1.val;
            int val2 = head2 == null ? 0 : head2.val;
            int sum = (val1 + val2 + col) % 10;
            col = (val1 + val2 + col) / 10;
            head.next = new ListNode(sum);
            head = head.next;
            head1 = head1 == null ? null : head1.next;
            head2 = head2 == null ? null : head2.next;
        }

        if (col > 0) {
            head.next = new ListNode(col);
        }
        return node.next;
    }
}
