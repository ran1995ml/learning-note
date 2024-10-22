package com.ran.leetcode.dfs;

/**
 * NumberOfIslands_200
 *
 * @author rwei
 * @since 2024/9/12 14:13
 */
public class NumberOfIslands_200 {
    private final int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        NumberOfIslands_200 obj = new NumberOfIslands_200();
        char[][] grid = {{'1', '1', '0', '0', '0'},
                         {'1', '1', '0', '0', '0'},
                         {'0', '0', '1', '0', '0'},
                         {'0', '0', '0', '1', '1'}};
        System.out.println(obj.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        for (int[] dir : dirs) {
            dfs(grid, i + dir[0], j + dir[1]);
        }
    }
}
