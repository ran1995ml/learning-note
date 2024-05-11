package com.ran.leetcode.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ListNode
 *
 * @author rwei
 * @since 2024/5/11 14:26
 */
public class ListNode {
    public int val;

    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode convertArray2LinkedList(int[] nums) {
        ListNode node = new ListNode(-1);
        ListNode head = node;
        for (int num : nums) {
            head.next = new ListNode(num);
            head = head.next;
        }
        return node.next;
    }

    public static int[] convertLinkedList2Array(ListNode head) {
        List<Integer> list = new ArrayList<>();

        while (head!=null) {
            list.add(head.val);
            head = head.next;
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
