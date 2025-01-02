package com.ran.leetcode.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * NQueens_51
 *
 * @author rwei
 * @since 2024/12/26 10:03
 */
public class NQueens_51 {
    public static void main(String[] args) {
        NQueens_51 obj = new NQueens_51();
        int n = 4;
        System.out.println(obj.solveNQueens(n));
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        dfs(ans, board, 0, n);
        return ans;
    }

    private void dfs(List<List<String>> ans, char[][] board, int i, int n) {
        if (i == n) {
            List<String> list = new ArrayList<>();
            for (char[] ch : board) {
                list.add(new String(ch));
            }
            ans.add(list);
            return;
        }
        for (int j = 0; j < n; j++) {
            if (isValid(board, i, j, n)) {
                board[i][j] = 'Q';
                dfs(ans, board, i + 1, n);
                board[i][j] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int i1, int j1, int n) {
        for (int i = 0; i < n; i++) {
            if (board[i][j1] == 'Q') return false;
        }
        for (int j = 0; j < n; j++) {
            if (board[i1][j] == 'Q') return false;
        }
        for (int k = 1; i1 - k >= 0 && j1 - k >= 0; k++) {
            if (board[i1 - k][j1 - k] == 'Q') return false;
        }
        for (int k = 1; i1 - k >= 0 && j1 + k < n; k++) {
            if (board[i1 - k][j1 + k] == 'Q') return false;
        }
        for (int k = 1; i1 + k < n && j1 - k >= 0; k++) {
            if (board[i1 + k][j1 - k] == 'Q') return false;
        }
        for (int k = 1; i1 + k < n && j1 + k < n; k++) {
            if (board[i1 + k][j1 + k] == 'Q') return false;
        }
        return true;
    }
}
