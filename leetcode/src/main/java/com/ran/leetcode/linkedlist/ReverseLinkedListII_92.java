package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;
import java.util.List;

/**
 * ReverseLinkedListII_92
 * 1,2,3,4,5,6
 * 1,3,2,4,5,6
 * 1,4,3,2,5,6
 * 1,5,4,3,2,6
 *
 * @author rwei
 * @since 2024/7/15 23:37
 */
public class ReverseLinkedListII_92 {
    public static void main(String[] args) {
        ReverseLinkedListII_92 obj = new ReverseLinkedListII_92();
        int[] nums = {1, 2, 3, 4, 5, 6};
        int left = 2;
        int right = 5;
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode listNode = obj.reverseBetween(head, left, right);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(listNode)));
    }

    //-1->1->2->3->4->5->6
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        for (int i = left; i < right; i++) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return node.next;
    }
}
