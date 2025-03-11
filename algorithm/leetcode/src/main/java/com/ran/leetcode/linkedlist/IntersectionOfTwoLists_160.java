package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.List;

/**
 * IntersectionOfTwoLists_160
 *
 * @author rwei
 * @since 2024/9/9 11:16
 */
public class IntersectionOfTwoLists_160 {
    public static void main(String[] args) {
        IntersectionOfTwoLists_160 obj = new IntersectionOfTwoLists_160();
        int[] nums1 = {4, 1, 8, 4, 5};
        int[] nums2 = {5, 6, 1};
        ListNode headA = ListNode.convertArray2LinkedList(nums1);
        ListNode headB = ListNode.convertArray2LinkedList(nums2);
        ListNode nodeA3 = ListNode.findNthNode(headA, 3);
        ListNode nodeB3 = ListNode.findNthNode(headB, 3);
        nodeB3.next = nodeA3;
        ListNode intersectionNode = obj.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode.val);
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }
}
