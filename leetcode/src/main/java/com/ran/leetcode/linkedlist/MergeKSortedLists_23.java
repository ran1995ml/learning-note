package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * MergeKSortedLists_23
 *
 * @author rwei
 * @since 2025/2/6 15:31
 */
public class MergeKSortedLists_23 {
    public static void main(String[] args) {
        MergeKSortedLists_23 obj = new MergeKSortedLists_23();
        int[] list1 = {1, 4, 5};
        int[] list2 = {1, 3, 4};
        int[] list3 = {2, 6};
        ListNode p1 = ListNode.convertArray2LinkedList(list1);
        ListNode p2 = ListNode.convertArray2LinkedList(list2);
        ListNode p3 = ListNode.convertArray2LinkedList(list3);
        ListNode[] lists = new ListNode[3];
        lists[0] = p1;
        lists[1] = p2;
        lists[2] = p3;
        ListNode node = obj.mergeKLists(lists);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(node)));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        if (lists.length == 1) return lists[0];
        if (lists.length == 2) return mergeLists(lists[0], lists[1]);
        ListNode[] lists1 = Arrays.copyOfRange(lists, 0, lists.length / 2);
        ListNode[] lists2 = Arrays.copyOfRange(lists, lists.length / 2, lists.length);
        return mergeLists(mergeKLists(lists1), mergeKLists(lists2));
    }

    private ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;
        if (l1 == null || l2 == null) return l1 == null ? l2 : l1;
        if (l1.val < l2.val) {
            l1.next = mergeLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeLists(l1, l2.next);
            return l2;
        }
    }
}
