package com.ran.leetcode.matrix;

import java.util.Arrays;

/**
 * DiagonalOrder_498
 *
 * @author rwei
 * @since 2024/10/23 10:45
 */
public class DiagonalOrder_498 {
    public static void main(String[] args) {
        DiagonalOrder_498 obj = new DiagonalOrder_498();
        int[][] mat = {{1, 2, 3},
                       {4, 5, 6},
                       {7, 8, 9}};
        System.out.println(Arrays.toString(obj.findDiagonalOrder(mat)));
    }

    public int[] findDiagonalOrder(int[][] mat) {
        int row = mat.length;
        int col = mat[0].length;
        int[] ans = new int[row * col];
        int index = 0;
        int i = 0;
        int j = 0;
        while (index < ans.length) {
            ans[index++] = mat[i][j];
            if ((i + j) % 2 == 0) {
                if (j == col - 1) {
                    i++;
                } else if (i == 0) {
                    j++;
                } else {
                    i--;
                    j++;
                }
            } else {
                if (i == row - 1) {
                    j++;
                } else if (j == 0) {
                    i++;
                } else {
                    i++;
                    j--;
                }
            }
        }
        return ans;
    }
}
