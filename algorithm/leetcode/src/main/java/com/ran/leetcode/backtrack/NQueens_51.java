package com.ran.leetcode.backtrack;

import org.w3c.dom.ls.LSException;

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
        char[][] queens = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(queens[i], '.');
        }
        List<List<String>> ans = new ArrayList<>();
        dfs(ans, queens, n, 0);
        return ans;
    }

    private void dfs(List<List<String>> ans, char[][] queens, int n, int index) {
        if (index == n) {
            List<String> list = new ArrayList<>();
            for (char[] queen : queens) {
                list.add(new String(queen));
            }
            ans.add(list);
            return;
        }
        char[] queen = queens[index];
        for (int i = 0; i < n; i++) {
            if (isValid(queens, index, i, n)) {
                queen[i] = 'Q';
                dfs(ans, queens, n, index + 1);
                queen[i] = '.';
            }
        }
    }

    private boolean isValid(char[][] queens, int i1, int j1, int n) {
        for (int i = 0; i < n; i++) {
            if (queens[i][j1] == 'Q') return false;
        }
        for (int j = 0; j < n; j++) {
            if (queens[i1][j] == 'Q') return false;
        }
        for (int i = i1 - 1, j = j1 - 1; i >= 0 && j >= 0; i--, j--) {
            if (queens[i][j] == 'Q') return false;
        }
        for (int i = i1 + 1, j = j1 - 1; i < n && j >= 0; i++, j--) {
            if (queens[i][j] == 'Q') return false;
        }
        for (int i = i1 - 1, j = j1 + 1; i >= 0 && j < n; i--, j++) {
            if (queens[i][j] == 'Q') return false;
        }
        for (int i = i1 + 1, j = j1 + 1; i < n && j < n; i++, j++) {
            if (queens[i][j] == 'Q') return false;
        }
        return true;
    }
}
