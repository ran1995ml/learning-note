package com.ran.leetcode.backtrack;

/**
 * WordSearch_79
 *
 * @author rwei
 * @since 2024/7/4 16:37
 */
public class WordSearch_79 {
    public static void main(String[] args) {
        WordSearch_79 obj = new WordSearch_79();
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(obj.exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[] visited = new boolean[row * col];
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, word, visited, dirs, 0, i, j, row, col)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, boolean[] visited, int[][] dirs, int index, int i, int j, int row, int col) {
        if (index == word.length()) return true;
        if (i < 0 || j < 0 || i >= row || j >= col || visited[i * col + j] || word.charAt(index) != board[i][j]) return false;
        visited[i * col + j] = true;
        for (int[] dir : dirs) {
            if (dfs(board, word, visited, dirs, index + 1, i + dir[0], j + dir[1], row, col)) return true;
        }
        visited[i * col + j] = false;
        return false;
    }
}
