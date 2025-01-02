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
 *
 * 先更新Map，再更新Node
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
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public int get(int key) {
        if (!this.map.containsKey(key)) return -1;
        Node node = new Node(key, this.map.get(key).value);
        removeNode(this.map.get(key));
        addToHead(node);
        this.map.put(key, node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);
        if (this.map.containsKey(key)) {
            removeNode(this.map.get(key));
        } else {
            if (this.capacity == this.map.size()) {
                this.map.remove(this.tail.pre.key);
                removeNode(this.tail.pre);
            }
        }
        this.map.put(key, node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        this.head.next.pre = node;
        node.next = this.head.next;
        node.pre = this.head;
        this.head.next = node;
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
