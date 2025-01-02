package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Stack;

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
        ListNode middle = getMiddleListNode(head);
        ListNode p1 = reverseListNode(middle);
        ListNode p2 = head;
        while (p1 != null) {
            if (p1.val != p2.val) return false;
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }

    private ListNode getMiddleListNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = head;
        ListNode p2 = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
        }
        return p2;
    }

    private ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) return head;
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
