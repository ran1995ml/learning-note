package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * RemoveLinkedListElements_203
 *
 * @author rwei
 * @since 2024/9/12 13:13
 */
public class RemoveLinkedListElements_203 {
    public static void main(String[] args) {
        RemoveLinkedListElements_203 obj = new RemoveLinkedListElements_203();
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        int val = 6;
        ListNode node = obj.removeElements(head, val);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(node)));
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode p = node;
        while (p != null && p.next != null) {
            if (p.next.val == val) {
                p.next = p.next.next;
            } else {
                p = p.next;
            }
        }
        return node.next;
    }
}
