package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * RemoveNthFromEnd_19
 * 双指针解
 *
 * @author rwei
 * @since 2024/5/19 18:18
 */
public class RemoveNthFromEnd_19 {
    public static void main(String[] args) {
        //  -1 1 2 3 4   5 null
        //               1 2
        int[] arrays = {1, 2, 3, 4, 5};
        ListNode head = ListNode.convertArray2LinkedList(arrays);
        int n = 3;
        RemoveNthFromEnd_19 obj = new RemoveNthFromEnd_19();
        ListNode listNode = obj.removeNthFromEnd(head, n);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(listNode)));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode p1 = node;
        ListNode p2 = node;
        while (n + 1 > 0) {
            p1 = p1.next;
            n--;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p2.next = p2.next.next;
        return node.next;
    }
}
