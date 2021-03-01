package com.leetcode.easy;

/**
 * 给你一个二维整数数组 matrix， 返回 matrix 的 转置矩阵 。
 *
 * 矩阵的 转置 是指将矩阵的主对角线翻转，交换矩阵的行索引与列索引。
 *
 * @since 2021-02-25
 */
public class LeetCode867 {
	public int[][] transpose(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		int[][] res = new int[col][row];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				res[j][i] = matrix[i][j];
			}
		}
		return res;
	}
}
