package com.leetcode.hard;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode37 { // 使用回溯的思路
    int n = 9;
    public void solveSudoku(char[][] board) {
        backtrack(board,0,0);
    }

    boolean backtrack(char[][] board, int i, int j) { // 这种有返回值的回溯表示只要找到一个可以解就不继续再尝试了
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

// 官方解法
// class Solution {
//    private boolean[][] line = new boolean[9][9];
//    private boolean[][] column = new boolean[9][9];
//    private boolean[][][] block = new boolean[3][3][9];
//    private boolean valid = false;
//    private List<int[]> spaces = new ArrayList<int[]>();
//
//    public void solveSudoku(char[][] board) {
//        for (int i = 0; i < 9; ++i) {
//            for (int j = 0; j < 9; ++j) {
//                if (board[i][j] == '.') {
//                    spaces.add(new int[]{i, j});
//                } else {
//                    int digit = board[i][j] - '0' - 1;
//                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
//                }
//            }
//        }
//
//        dfs(board, 0);
//    }
//
//    public void dfs(char[][] board, int pos) {
//        if (pos == spaces.size()) {
//            valid = true;
//            return;
//        }
//
//        int[] space = spaces.get(pos);
//        int i = space[0], j = space[1];
//        for (int digit = 0; digit < 9 && !valid; ++digit) {
//            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
//                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
//                board[i][j] = (char) (digit + '0' + 1);
//                dfs(board, pos + 1);
//                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
//            }
//        }
//    }
//}
