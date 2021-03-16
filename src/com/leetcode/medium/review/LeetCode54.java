/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 *
 * @since 2020-06-05
 */
public class LeetCode54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new LinkedList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return res;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0;
        int right = col - 1;
        int top = 0;
        int bottom = row - 1;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            for (int i = top + 1; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }

            if (left < right && top < bottom) { // while的循环条件只能保证是小于等于，上面的两个逻辑可以处理等于的情况
                for (int j = right - 1; j > left; j--) {
                    res.add(matrix[bottom][j]);
                }
                for (int i = bottom; i > top; i--) {
                    res.add(matrix[i][left]);
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return res;
    }
}

//     public List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> res = new LinkedList<>();
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
//            return res;
//        }
//        int n = matrix[0].length;
//        int m = matrix.length;
//        for (int i = 0; i < (Math.min(m, n) + 1) / 2; i++) {
//            for (int j = i; j < n - i; j++) {
//                res.add(matrix[i][j]);
//            }
//            for (int j = i + 1; j < m - i; j++) {
//                res.add(matrix[j][n - 1 - i]);
//            }
//// m - 1 - i 是指随着层数增加时，层数的边界所在行（即最上行和最下行的所处的行数），
//// 如果出现最上行和最下行是同一行的情况（比如：3行5列的矩阵中，第二层是1行3列的矩阵），
//// 此时按顺序打印完第二层第一行后，第一列为空，不打印，折返后如果没有（m - 1 - i != i）这个限制，
//// 会重新打印第二层的第一行，造成结果的值变多。同理可得，n - 1 - i != i。
//            for (int j = i + 1; j < n - i && (m-1-i != i); j++) {
//                res.add(matrix[m - 1 - i][n - 1 - j]);
//            }
//            for (int j = i + 1; j < m - 1 - i && (n-1-i) != i; j++) {
//                res.add(matrix[m - 1 - j][i]);
//            }
//        }
//        return res;
//    }

