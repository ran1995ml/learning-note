package com.ran.leetcode.array;

/**
 * FindDuplicate_287
 *
 * @author rwei
 * @since 2024/10/27 18:54
 */
public class FindDuplicate_287 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        FindDuplicate_287 obj = new FindDuplicate_287();
        System.out.println(obj.findDuplicate(nums));
    }

    public int findDuplicate(int[] nums) {
        int p1 = 0;
        int p2 = 0;
        do {
            p1 = nums[p1];
            p1 = nums[p1];
            p2 = nums[p2];
        } while (p1 != p2);
        p1 = 0;
        while (p1 != p2) {
            p1 = nums[p1];
            p2 = nums[p2];
        }
        return p1;
    }
}
