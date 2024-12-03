package com.ran.leetcode.matrix;

/**
 * SearchMatrixII_240
 *
 * @author rwei
 * @since 2024/9/30 16:20
 */
public class SearchMatrixII_240 {
    public static void main(String[] args) {
        SearchMatrixII_240 obj = new SearchMatrixII_240();
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
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
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }
}
