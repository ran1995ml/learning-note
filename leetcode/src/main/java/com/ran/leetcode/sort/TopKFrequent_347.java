package com.ran.leetcode.sort;

import java.util.*;

/**
 * TopKFrequent_347
 *
 * @author rwei
 * @since 2024/11/12 15:19
 */
public class TopKFrequent_347 {
    public static void main(String[] args) {
        TopKFrequent_347 obj = new TopKFrequent_347();
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println(Arrays.toString(obj.topKFrequent(nums, k)));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o2) - map.get(o1);
            }
        });
        maxHeap.addAll(map.keySet());
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = maxHeap.poll();
        }
        return ans;
    }
}
