package com.leetcode.hard;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class LeetCode42 {
    public int trap(int[] height) {
        int res = 0;
        Deque<Integer> s = new LinkedList<>();
        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[s.peek()] < height[i]) {
                int top = s.pop();
                if (s.isEmpty()) {
                    break;
                }

                int dis = i - s.peek() - 1;
                int h = Math.min(height[i], height[s.peek()]) - height[top];
                res += h * dis;
            }
            s.push(i);
        }

        return res;
    }
}
