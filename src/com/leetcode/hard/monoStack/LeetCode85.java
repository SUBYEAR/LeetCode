package com.leetcode.hard.monoStack;

import java.util.LinkedList;

/**
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 */
public class LeetCode85 {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int row = matrix.length;
        int col = matrix[0].length;
        int[] heights = new int[col];
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') {
                    heights[j] = 0;
                } else {
                    ++heights[j];
                }
            }
            res = Math.max(res, getArea(heights));
        }
        return res;
    }

    private int getArea(int[] arr) {
        // 单调递增栈
        int n = arr.length;
        int[] newArr = new int[n + 1];
        for (int i = 0; i < n; i++) {
            newArr[i] = arr[i];
        }
        newArr[n] = -1;
        LinkedList<Integer> st = new LinkedList<Integer>();
        int res = 0;

        for (int i = 0; i <= n; i++) {
            while (!st.isEmpty() && newArr[st.getLast()] >= newArr[i]) {
                int r = st.pollLast(); // 计算的是出栈的元素
                int l = st.size() > 0 ? st.peekLast() : -1;
                res = Math.max(res, (i - l - 1) * newArr[r]);
            }
            st.addLast(i);
        }
        return res;
    }
}
