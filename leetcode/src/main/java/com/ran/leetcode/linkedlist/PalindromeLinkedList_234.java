package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

/**
 * PalindromeLinkedList_234
 *
 * @author rwei
 * @since 2024/9/23 13:45
 */
public class PalindromeLinkedList_234 {
    public static void main(String[] args) {
        PalindromeLinkedList_234 obj = new PalindromeLinkedList_234();
        int[] nums = {1, 2, 3, 4, 5, 3, 2, 1};
        ListNode head = ListNode.convertArray2LinkedList(nums);
        System.out.println(obj.isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        ListNode middleNode = getMiddleNode(head);
        ListNode p1 = head;
        ListNode p2 = middleNode.next;
        middleNode.next = null;
        ListNode p3 = reverseList(p2);
        while (p1 != null && p3 != null) {
            if (p1.val != p3.val) return false;
            p1 = p1.next;
            p3 = p3.next;
        }
        return true;
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

    private ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
