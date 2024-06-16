package com.ran.leetcode.matrix;

import java.util.Arrays;

/**
 * SpiralMatrixII_59
 *
 * @author rwei
 * @since 2024/6/12 10:02
 */
public class SpiralMatrixII_59 {
    private int value = 1;

    public static void main(String[] args) {
        SpiralMatrixII_59 obj = new SpiralMatrixII_59();
        int n = 3;
        System.out.println(Arrays.deepToString(obj.generateMatrix(n)));
    }

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int i1 = 0;
        int j1 = 0;
        int i2 = n - 1;
        int j2 = n - 1;
        while (i1 <= i2 && j1 <= j2) {
            traverse(matrix, i1, j1, i2, j2);
            i1++;
            j1++;
            i2--;
            j2--;
        }
        return matrix;
    }

    public void traverse(int[][] matrix, int i1, int j1, int i2, int j2) {
        if (i1 == i2) {
            for (int j = j1; j <= j2; j++) {
                matrix[i1][j] = value++;
            }
        } else if (j1 == j2) {
            for (int i = i1; i <= i2; i++) {
                matrix[i][j1] = value++;
            }
        } else {
            for (int j = j1; j < j2; j++) {
                matrix[i1][j] = value++;
            }
            for (int i = i1; i < i2; i++) {
                matrix[i][j2] = value++;
            }
            for (int j = j2; j > j1; j--) {
                matrix[i2][j] = value++;
            }
            for (int i = i2; i > i1; i--) {
                matrix[i][j1] = value++;
            }
        }
    }
}
