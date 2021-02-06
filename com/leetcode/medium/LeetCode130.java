package com.leetcode.medium;

/*
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
被围绕的区间不会存在于边界上，换句话说，
任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
*/
/*
给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 */
public class LeetCode130 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0) return;
        int row = board.length;
        int col = board[0].length;
        boolean[][] visit = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            if (board[i][0] != 'X')
                fill(board, i, 0, visit);
            if (board[i][col - 1] != 'X')
                fill(board, i, col - 1, visit);
        }
        for (int j = 1; j < col - 1; j++) {
            if (board[0][j] != 'X')
                fill(board, 0, j, visit);
            if (board[row - 1][j] != 'X')
                fill(board, row - 1, j, visit);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'Z') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }


    void fill(char[][] board, int x, int y, boolean[][] visit) {
        if (!isInArea(board.length, board[0].length, x, y)) {
            return;
        }

        if (visit[x][y] || board[x][y] == 'X') {
            return;
        }

        visit[x][y] = true;
        board[x][y] = 'Z';
        fill(board, x + 1, y, visit);
        fill(board, x - 1, y, visit);
        fill(board, x, y + 1, visit);
        fill(board, x, y - 1, visit);
    }

    boolean isInArea(int row, int col, int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}
