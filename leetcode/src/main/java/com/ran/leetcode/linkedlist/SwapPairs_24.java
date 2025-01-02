package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * SwapPairs_24
 *
 * @author rwei
 * @since 2024/12/24 21:16
 */
public class SwapPairs_24 {
    public static void main(String[] args) {
        SwapPairs_24 obj = new SwapPairs_24();
        int[] nums = {1, 2, 3, 4};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode node = obj.swapPairs(head);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(node)));
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = cur;
            pre.next = next;
            pre = cur;
            cur = cur.next;
        }
        return node.next;
    }
}
