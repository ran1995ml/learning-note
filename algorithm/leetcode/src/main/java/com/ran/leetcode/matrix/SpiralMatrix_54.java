package com.ran.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * SpiralMatrix_54
 *
 * @author rwei
 * @since 2024/6/3 15:12
 */
public class SpiralMatrix_54 {
    public static void main(String[] args) {
        SpiralMatrix_54 obj = new SpiralMatrix_54();
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> ans = obj.spiralOrder(matrix);
        System.out.println(ans.toString());
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        int i0 = 0;
        int j0 = 0;
        int i1 = row - 1;
        int j1 = col - 1;
        while (i0 <= i1 && j0 <= j1) {
            traverse(ans, matrix, i0++, j0++, i1--, j1--);
        }
        return ans;
    }

    private void traverse(List<Integer> ans, int[][] matrix, int i0, int j0, int i1, int j1) {
        if (i0 == i1) {
            for (int j = j0; j <= j1; j++) {
                ans.add(matrix[i0][j]);
            }
        } else if (j0 == j1) {
            for (int i = i0; i <= i1; i++) {
                ans.add(matrix[i][j0]);
            }
        } else {
            for (int j = j0; j < j1; j++) {
                ans.add(matrix[i0][j]);
            }
            for (int i = i0; i < i1; i++) {
                ans.add(matrix[i][j1]);
            }
            for (int j = j1; j > j0; j--) {
                ans.add(matrix[i1][j]);
            }
            for (int i = i1; i > i0; i--) {
                ans.add(matrix[i][j0]);
            }
        }
    }
}
