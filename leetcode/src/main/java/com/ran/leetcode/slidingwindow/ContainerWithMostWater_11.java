package com.ran.leetcode.slidingwindow;

/**
 * ContainerWithMostWater_11
 * 滑动窗口，贪心
 *
 * @author rwei
 * @since 2024/5/14 13:51
 */
public class ContainerWithMostWater_11 {
    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        ContainerWithMostWater_11 obj = new ContainerWithMostWater_11();
        System.out.println(obj.maxArea(height));
    }

    public int maxArea(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int w = right - left;
            if (height[right] > height[left]) {
                left++;
            } else {
                right--;
            }
            max = Math.max(max, w * h);
        }
        return max;
    }
}
