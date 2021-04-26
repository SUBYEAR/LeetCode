package com.leetcode.hard;

import java.util.Stack;
/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 */
public class LeetCode84 {
    int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] arr = new int[len + 1];
        arr[len] = 0;
        int index = 0;
        int result = 0;
        for (int num : heights) {
            arr[index++] = num;
        }

        Stack<Integer> stack = new Stack();
        for (index = 0; index < arr.length; index++) {
            // 也是单调递减栈这一题与接雨水的区别是弹出栈时计算弹出元素对应的值
            // 为了维护单调性需要弹出元素，在弹出元素的时候需要计算它对应的结果值
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[index]) {
                int h = arr[stack.peek()];
                stack.pop();
                int left = stack.size() > 0 ? stack.peek(): -1; // 接雨水的栈为空时没有左边界了要break，而这里可以继续计算
                result = Math.max(result, h * (index - left - 1));
            }
            stack.push(index); // 这里是递增栈，接雨水是递减栈
        }
        return result;
    }
}
