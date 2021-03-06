package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 *
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 *
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-kth-largest-xor-coordinate-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1738 {
    public int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] pre = new int[m][n + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pre[i][j + 1] = pre[i][j] ^ matrix[i][j];
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int b = 0; b < n; b++) {
            int ab = 0;
            for (int a = 0; a < m; a++) {
                ab ^= pre[a][b + 1];
                list.add(ab);
            }
        }
        list.sort((o1, o2) -> o2 - o1);
        return list.get(k - 1);
    }
}

// 或者考虑使用大小位k的数组维护第k大的数
//            int add = 0;
//            while (add < 4 && maxn[add] > nums[i]) { // 索引在前值越大
//                add++;
//            }
//            if (add < 4) {
//                for (int j = 3; j > add; j--) {
//                    maxn[j] = maxn[j - 1]; // 整体后移出一个空位插入值
//                }
//                maxn[add] = nums[i];
//            }