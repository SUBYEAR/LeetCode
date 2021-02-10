package com.leetcode.hard;

/*
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。
 */
public class LeetCode37 { // 使用回溯的思路
    int n = 9;
    public void solveSudoku(char[][] board) {
        backtrack(board,0,0);
    }

    boolean backtrack(char[][] board, int i, int j) {
        // 注意这里边界条件放置的顺序
        if (j == n) {
            return backtrack(board, i + 1, 0);
        }

        if (i == n) {
            return true;
        }

        if (board[i][j] != '.') {
            return backtrack(board, i, j + 1);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (!isAccessable(board, i, j, c)) {
                continue;
            }
            board[i][j] = c;
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    boolean isAccessable(char[][] board, int row, int col, char c) {
        for (int index = 0; index < n; index++) {
            if (board[index][col] == c) return false;
            if (board[row][index] == c) return false;
            if (board[(row / 3) * 3 + index / 3][(col / 3) * 3 + index % 3] == c) return false;
        }
        return true;
    }
}
