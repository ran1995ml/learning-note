package com.ran.leetcode.array;

import java.util.Arrays;

/**
 * RotateImage_48
 *
 * @author rwei
 * @since 2024/5/31 14:55
 */
public class RotateImage_48 {
    public static void main(String[] args) {
        RotateImage_48 obj = new RotateImage_48();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        obj.rotate(matrix);
        System.out.println(Arrays.toString(matrix));
    }

    public void rotate(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        rotate1(matrix, row, col);
        rotate2(matrix, row, col);
    }

    private void rotate1(int[][] matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = i; j < col; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void rotate2(int[][] matrix, int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][col - j - 1];
                matrix[i][col - j - 1] = temp;
            }
        }
    }
}
