package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * ReverseLinkedList_206
 *
 * @author rwei
 * @since 2024/9/12 13:20
 */
public class ReverseLinkedList_206 {
    public static void main(String[] args) {
        ReverseLinkedList_206 obj = new ReverseLinkedList_206();
        int[] nums = {1, 2, 3, 4, 5};
        ListNode node = obj.reverseList(ListNode.convertArray2LinkedList(nums));
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(node)));
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
