package com.leetcode.medium.passed.arrayAndMatrix;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。
 */
public class LeetCode73 {
    public void setZeroes(int[][] matrix) {
        if (matrix == null) return;
        boolean[][] visit = new boolean[matrix.length][matrix[0].length];
        for (int i = 0;  i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    visit[i][j] = true;
                }
            }
        }

        for (int i = 0;  i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (visit[i][j] && matrix[i][j] == 0) {
                    setZero(matrix, i, j, visit);
                }
            }
        }
    }

    void setZero(int[][] matrix, int row, int col, boolean[][] visit) {
        if (!visit[row][col]) return;

        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }

        for (int i = 0; i < matrix.length; i++) {
            matrix[i][col] = 0;
        }
        visit[row][col] = false;
    }
}
