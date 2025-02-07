package com.ran.leetcode.slidingwindow;

import sun.awt.image.ImageWatched;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * MaxSlidingWindow_239
 *
 * @author rwei
 * @since 2024/9/20 10:21
 */
public class MaxSlidingWindow_239 {
    public static void main(String[] args) {
        MaxSlidingWindow_239 obj = new MaxSlidingWindow_239();
        int[] nums = {1, 3, 1, 2, 0, 5};
        int k = 3;
        System.out.println(Arrays.toString(obj.maxSlidingWindow(nums, k)));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]) {
                queue.pollLast();
            }
            queue.addLast(i);
            if (i == queue.peekFirst() + k) {
                queue.pollFirst();
            }
            if (i >= k - 1) {
                ans[i - k + 1] = nums[queue.peekFirst()];
            }
        }
        return ans;
    }
}
