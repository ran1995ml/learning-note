package com.ran.leetcode.math;

/**
 * HammingDistance_461
 *
 * @author rwei
 * @since 2024/10/27 19:52
 */
public class HammingDistance_461 {
    public static void main(String[] args) {
        HammingDistance_461 obj = new HammingDistance_461();
        int x = 3;
        int y = 1;
        System.out.println(obj.hammingDistance(x, y));
    }

    public int hammingDistance(int x, int y) {
        int value = x ^ y;
        int distance = 0;
        while (value > 0) {
            if (value % 2 > 0) distance++;
            value = value / 2;
        }
        return distance;
    }
}
