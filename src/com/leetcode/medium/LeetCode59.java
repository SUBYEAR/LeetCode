package com.leetcode.medium;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 * 示例 1：
 *
 * 输入：n = 3
 * 输出：[[1,2,3],[8,9,4],[7,6,5]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/spiral-matrix-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode59 {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int index = 1;
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        while (left <= right && top <= bottom) {
            for (int j = left; j <= right; j++) {
                res[top][j] = index++;
            }
            for (int i = top + 1; i <= bottom; i++) {
                res[i][right] = index++;
            }

            if (right > left && bottom > top) {
                for (int j = right - 1; j > left; j--) {
                    res[bottom][j] = index++;
                }
                for (int i = bottom; i > top; i--) {
                    res[i][left] = index++;
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
