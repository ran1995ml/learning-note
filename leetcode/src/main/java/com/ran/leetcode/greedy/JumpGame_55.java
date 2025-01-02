package com.ran.leetcode.greedy;

/**
 * JumpGame_55
 *
 * @author rwei
 * @since 2024/6/4 16:10
 */
public class JumpGame_55 {
    public static void main(String[] args) {
        JumpGame_55 obj = new JumpGame_55();
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(obj.canJump(nums));
    }

    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (max < i) break;
            max = Math.max(max, i + nums[i]);
        }
        return max >= nums.length - 1;
    }
}
