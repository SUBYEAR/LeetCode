/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.Collections;
import java.util.Stack;

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @since 2020-05-30
 */
public class LeetCode84 {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] tempArr = new int[len + 1];
        int i = 0;
        for (int val : heights) {
            tempArr[i++] = val;
        }
        Stack<Integer> s = new Stack<>();
        int res = 0;

        for (i = 0; i < tempArr.length; i++) {
            while (s.size() > 0 && tempArr[s.peek()] >= tempArr[i]) {
                int height = tempArr[s.peek()];
                s.pop();
                int left = s.size() > 0 ? s.peek() : -1;
                res = Math.max(res, height * (i - left - 1));
            }
            s.push(i);
        }
        return res;
    }
}
