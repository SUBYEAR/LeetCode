package com.leetcode.hard.dp;

/**
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 *
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-submatrix-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Interview1724 {
    public int[] getMaxMatrix(int[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
        int sum = 0;
        int[] ans = new int[4];
        int maxSum = Integer.MIN_VALUE;
        int left = 0, top = 0;

        for (int i = 0; i < N; i++) {
            int[] sumArr = new int[M];
            for (int j = i; j < N; j++) {
                sum = 0;
                for (int k = 0; k < M; k++) {
                    sumArr[k] += matrix[j][k];

                    if (sum > 0) {
                        sum += sumArr[k];
                    } else { // 重新开始计数左上角的点重置
                        sum = sumArr[k];
                        left = i;
                        top = k;
                    }

                    if (sum > maxSum) {
                        maxSum = sum;
                        ans[0] = left;
                        ans[1] = top;
                        ans[2] = j;
                        ans[3] = k;
                    }
                }
            }
        }

        return ans;
    }
}
