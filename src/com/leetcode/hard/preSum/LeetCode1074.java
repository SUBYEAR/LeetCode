package com.leetcode.hard.preSum;

import java.util.HashMap;
import java.util.Map;

/**
 * 给出矩阵 matrix 和目标值 target，返回元素总和等于目标值的非空子矩阵的数量。
 *
 * 子矩阵 x1, y1, x2, y2 是满足 x1 <= x <= x2 且 y1 <= y <= y2 的所有单元 matrix[x][y] 的集合。
 *
 * 如果 (x1, y1, x2, y2) 和 (x1', y1', x2', y2') 两个子矩阵中部分坐标不同（如：x1 != x1'），那么这两个子矩阵也不同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-submatrices-that-sum-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1074 {
    // 枚举子矩阵的上下边界，并计算出该边界内每列的元素和，则原问题转换成了如下一维问题
    // 给定一个整数数组和一个整数 target，计算该数组中子数组和等于 target 的子数组个数【LeetCode560】
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int ans = 0;
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; j++) { // 枚举下边界
                for (int col = 0; col < n; col++) {
                    sum[col] += matrix[j][col];
                }
                ans += subarraySum(sum, target);
            }
        }
        return ans;
    }

    private int subarraySum(int[] arr, int target) {
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        int ans = 0;
        int sum = 0;
        for (int num : arr) {
            sum += num;
            if (preSum.containsKey(sum - target)) {
                ans += preSum.get(sum - target);
            }
            preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
