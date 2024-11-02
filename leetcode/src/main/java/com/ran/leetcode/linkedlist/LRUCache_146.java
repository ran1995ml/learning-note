package com.ran.leetcode.linkedlist;

import com.ran.leetcode.entity.ListNode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LRUCache_146
 * 实现需要双向链表和哈希表
 * 双向链表维护元素位置，体现优先级，便于删除
 * 哈希表用于快速访问
 * @author rwei
 * @since 2024/10/22 16:45
 */
public class LRUCache_146 {
    private int capacity;

    private Map<Integer, Node> map;

    private Node head;

    private Node tail;

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            Node node = new Node(key, map.get(key).value);
            removeNode(map.get(key));
            addToHead(node);
            map.put(key, node);
            return node.value;
        }
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            removeNode(map.get(key));
        } else if (map.size() == capacity) {
            map.remove(tail.pre.key);
            removeNode(tail.pre);
        }
        addToHead(node);
        map.put(key, node);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.pre = head;
        head.next.pre = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private class Node {
        private int key;

        private int value;

        private Node pre;

        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
