package com.ran.leetcode.sort;

import java.util.PriorityQueue;

/**
 * FindKthLargest_215
 * 使用PriorityQueue小顶堆的特效，poll会将堆顶元素抛出
 * @author rwei
 * @since 2024/9/30 15:07
 */
public class FindKthLargest_215 {
    public static void main(String[] args) {
        FindKthLargest_215 obj = new FindKthLargest_215();
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        System.out.println(obj.findKthLargest(nums, k));
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
}
