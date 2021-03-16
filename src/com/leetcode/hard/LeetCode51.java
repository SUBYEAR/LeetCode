package com.leetcode.hard;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 */
public class LeetCode51 {
    int count = 0;
    List<List<String>> res = new LinkedList<>();
    public List<List<String>> solveNQueens(int n) {
        helper(n,0, new int[n]);
        return res;
    }

    // 回溯算法，这里使用了一个辅助数组columns
    void helper(int n, int row, int[] columns) {
        if (row == n) {
            String[] arr = new String[n];
            List<String> temp = new LinkedList<>();
            for(int r = 0; r < columns.length; r++) {
                Arrays.fill(arr, "");
                arr[columns[r]] = "Q";
                String join = String.join("", arr);
                temp.add(join);
            }

            res.add(temp);
            ++count;
            return;
        }

        for (int col = 0; col < n; col++) {
            columns[row] = col; // 第row行皇后的摆放位置是col列
            if (check(row, col, columns)) {
                helper(n, row + 1, columns);
            }
            columns[row] = -1;
        }

    }

    boolean check(int row, int col, int[] columns) {
        for (int r = 0; r < row; r++) {
            if (col == columns[r] || row - r == Math.abs(col - columns[r])) {
                return false;
            }
        }
        return true;
    }
}
