package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * ReverseKGroup_25
 *
 * @author rwei
 * @since 2025/1/12 20:50
 */
public class ReverseKGroup_25 {
    public static void main(String[] args) {
        ReverseKGroup_25 obj = new ReverseKGroup_25();
        int[] nums = {1, 2, 3, 4, 5, 6};
        int k = 3;
        ListNode head = ListNode.convertArray2LinkedList(nums);
        ListNode node = obj.reverseKGroup(head, k);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(node)));
    }

    // -1 -> 1 -> 2 -> 3 -> 4 -> 5
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        ListNode cur = node;
        while (cur.next != null) {
            for (int i = 0; i < k; i++) {
                cur = cur.next;
                if (cur == null) break;
            }
            if (cur == null) break;
            ListNode next = cur.next;
            ListNode p = pre.next;
            cur.next = null;
            pre.next = reverse(pre.next);
            p.next = next;
            pre = p;
            cur = p;
        }
        return node.next;
    }

    private ListNode reverse(ListNode head) {
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
