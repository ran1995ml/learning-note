package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.Arrays;

/**
 * MergeTwoLists_21
 *
 * @author rwei
 * @since 2024/5/19 21:21
 */
public class MergeTwoLists_21 {
    public static void main(String[] args) {
        int[] arrays1 = {1, 2, 4};
        int[] arrays2 = {1, 3, 4};
        ListNode list1 = ListNode.convertArray2LinkedList(arrays1);
        ListNode list2 = ListNode.convertArray2LinkedList(arrays2);
        MergeTwoLists_21 obj = new MergeTwoLists_21();
        ListNode lists = obj.mergeTwoLists(list1, list2);
        System.out.println(Arrays.toString(ListNode.convertLinkedList2Array(lists)));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) {
            return null;
        } else if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        } else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
