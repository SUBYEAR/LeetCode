package com.leetcode.medium.passed.presum;

/**
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2) 。
 * @since 2021-03-02
 */
public class LeetCode304 {
	private class NumMatrix {
		int[][] sum = null;
		public NumMatrix(int[][] matrix) {
			if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
				return;
			}
			int row = matrix.length;
			int col = matrix[0].length;
			sum = new int[row][col + 1];
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					sum[i][j + 1] = sum[i][j] + matrix[i][j];
				}
			}
		}

		public int sumRegion(int row1, int col1, int row2, int col2) {
			if (sum == null) {
				return 0;
			}
			int ans = 0;
			for (int i = row1; i <= row2; i++) {
				ans += sum[i][col2 + 1] - sum[i][col1];
			}
			return ans;
		}
	}
}
