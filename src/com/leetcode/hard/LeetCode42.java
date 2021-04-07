package com.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */
public class LeetCode42 {
    public int trap(int[] height) {
        int res = 0;
        Deque<Integer> s = new LinkedList<>();
        for (int i = 0; i < height.length; i++) { // 遍历当前元素时栈顶的元素就被当前元素和栈顶的前一个元素夹住了
            while (!s.isEmpty() && height[s.peek()] < height[i]) {
                int top = s.pop();
                if (s.isEmpty()) {
                    break;
                }

                int dis = i - s.peek() - 1;
                int h = Math.min(height[i], height[s.peek()]) - height[top];
                res += h * dis;
            }
            s.push(i); // 当前的条形块小于或等于栈顶的条形块，我们将条形块的索引入栈
        }

        return res;
    }
}
