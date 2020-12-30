/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * 功能描述
 *
 * @since 2020 -04-28
 */
public class MonoStack {
    /**
     * Solution int [ ].
     *
     * @param arr the arr
     * @return the int [ ]
     */
    public int[] solution(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[arr.length];
        Arrays.fill(result, -1);

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                int index = stack.pop();
                result[index] = i - index;
            }
            stack.push(i);
        }
        return result;
    }

    int largestRectangleArea(int[] height) {
        int res = 0;
        int[] arr = new int[height.length + 1];
        for (int i = 0; i < height.length; i++) {
            arr[i] = height[i];
        }
        arr[height.length] = 0;
        Stack<Integer> stack = new Stack<>();
        // 以当前栈顶为索引的元素的值大于当前i元素的值表示栈顶元素向右扩展的边缘已经出现，然后不断弹栈就得到左边界
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int h = arr[stack.peek()];
                stack.pop();
                int left = stack.size() > 0 ? stack.peek() : -1;
                res = Math.max(res, h * (i - left - 1));
            }
            stack.push(i);
        }
        return res;
    }
}
