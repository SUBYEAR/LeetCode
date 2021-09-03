package com.leetcode.hard.preSum;

import java.util.Arrays;
import java.util.TreeSet;

import static com.leetcode.Main.printArr;

/**
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 *
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode363 {
    int res = Integer.MIN_VALUE;
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) { // 枚举row的上下边界
            int[] sum = new int[col];
            for (int j = i; j < row; j++) {
                for (int c = 0; c < col; c++) {
                    sum[c] += matrix[j][c];
                }
                res = Math.max(res, help(sum, k));
            }
        }
        return res;
    }

    int help(int[] arr, int k) { // 连续子数组的和不超过k的最大值
        TreeSet<Integer> preSum = new TreeSet<>();
        int sum = 0, ans = Integer.MIN_VALUE;
        preSum.add(0);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            Integer gap = preSum.ceiling(sum - k); // ceiling是求大于该值的最小值
            if (gap != null) {
                ans = Math.max(ans, sum - gap); // 正难则反
            }
            preSum.add(sum);
        }

        return ans;
    }
}
