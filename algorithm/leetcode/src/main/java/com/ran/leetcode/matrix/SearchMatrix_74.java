package com.ran.leetcode.matrix;

/**
 * SearchMatrix_74
 *
 * @author rwei
 * @since 2024/6/12 17:33
 */
public class SearchMatrix_74 {
    public static void main(String[] args) {
        SearchMatrix_74 obj = new SearchMatrix_74();
        int[][] matrix = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}};
        int target = 3;
        System.out.println(obj.searchMatrix(matrix, target));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        int i = 0;
        int j = col - 1;
        while (i < row && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
