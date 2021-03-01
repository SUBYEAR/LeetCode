package com.leetcode.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 *
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/toeplitz-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] trans = trans(matrix, row, col);
        int length = trans.length;

        for (int k = 0; k <= Math.abs(col - row); k++) {
            int[][] arr = new int[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    arr[i][j] = trans[i][j + k];
                }
            }
            if (!isSatisfied(arr)) {
                return false;
            }
        }
        return true;
    }

    int[][] trans(int[][] matrix, int row, int col) {
        if (row <= col) {
            return matrix;
        }

        int min = matrix[0].length;
        int max = matrix.length;
        int[][] res = new int[min][max];
        for (int i = 0; i < min; i++) {
            for (int j = 0; j < max; j++) {
                res[i][j] = matrix[j][i];
            }
        }
        return res;
    }

    boolean isSatisfied(int[][] matrix) {
        //斜着打印数组
        for (int len = 1; len < matrix.length; len++) {
            Set<Integer> s1 = new HashSet<>();
            Set<Integer> s2 = new HashSet<>();
            for (int i = 0; i <= matrix.length - len; i ++) {
                int j = len + i - 1;
                s1.add(matrix[i][j]);
                s2.add(matrix[j][i]);
            }
            if (s1.size() != 1 || s2.size() != 1) {
                return false;
            }
        }
        return true;
    }
}
