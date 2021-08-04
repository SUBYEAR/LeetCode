package com.leetcode.hard.backtrack;

import java.util.Arrays;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回 n 皇后问题 不同的解决方案的数量
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode52 {
    int res = 0;
    public int totalNQueens(int n) {
        int[] column = new int[n];
        Arrays.fill(column, -1);
        backtrack(n, 0, column);
        return res;
    }

    void backtrack(int n, int row, int[] column) {
        if (row == n) {
            ++res;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (check(row, col, column)) {
                continue;
            }
            column[row] = col;
            backtrack(n, row + 1, column);
            column[row] = -1;
        }
    }

    boolean check(int row, int col, int[] column) {
        for (int i = 0; i < row; i++) {
            if (col == column[i] || row - i == Math.abs(col - column[i])) {
                return true;
            }
        }
        return false;
    }
}
