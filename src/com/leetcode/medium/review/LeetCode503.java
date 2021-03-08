/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 *
 *
 * @since 2020-06-19
 */
public class LeetCode503 {
    public int[] nextGreaterElementsII(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[] {};
        }
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        int lastMaxIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= nums[lastMaxIndex]) {
                lastMaxIndex = i;
            }
        }
        Deque<Integer> deque = new LinkedList<>();
        findMaxElement(deque, nums, lastMaxIndex + 1, nums.length, ans);
        findMaxElement(deque, nums, 0, lastMaxIndex + 1, ans);
        return ans;
    }

    private void findMaxElement(Deque<Integer> deque, int[] nums, int start, int end, int[] ans) {
        for (int i = start; i < end; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.getFirst()]) {
                int index = deque.removeFirst();
                ans[index] = nums[i];
            }
            deque.addFirst(i);
        }
    }

    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }
}
