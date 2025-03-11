package com.ran.leetcode.dfs;

/**
 * NumberOfIslands_200
 *
 * @author rwei
 * @since 2024/9/12 14:13
 */
public class NumberOfIslands_200 {
    public static void main(String[] args) {
        NumberOfIslands_200 obj = new NumberOfIslands_200();
        char[][] grid = {{'1', '1', '0', '0', '0'},
                         {'1', '1', '0', '0', '0'},
                         {'0', '0', '1', '0', '0'},
                         {'0', '0', '0', '1', '1'}};
        System.out.println(obj.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int sum = 0;
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    sum++;
                    dfs(dirs, grid, i, j, row, col);
                }
            }
        }
        return sum;
    }

    private void dfs(int[][] dirs, char[][] grid, int i, int j, int row, int col) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] == '0') return;
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            dfs(dirs, grid, i + dir[0], j + dir[1], row, col);
        }
    }
}
