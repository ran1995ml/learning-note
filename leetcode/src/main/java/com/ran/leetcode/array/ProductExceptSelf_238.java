package com.ran.leetcode.array;

import java.util.Arrays;

/**
 * ProductExceptSelf_238
 *
 * @author rwei
 * @since 2024/9/20 11:02
 */
public class ProductExceptSelf_238 {
    public static void main(String[] args) {
        ProductExceptSelf_238 obj = new ProductExceptSelf_238();
        int[] nums = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(obj.productExceptSelf(nums)));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, 1);

        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int temp = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            temp = temp * nums[i + 1];
            ans[i] = ans[i] * temp;
        }
        return ans;
    }
}
