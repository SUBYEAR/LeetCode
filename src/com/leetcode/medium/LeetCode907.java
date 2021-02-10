/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

import java.util.Stack;

/**
 * 给定一个整数数组 A，找到 min(B) 的总和，其中 B 的范围为 A 的每个（连续）子数组。
 * 由于答案可能很大，因此返回答案模 10^9 + 7。
 *  
 * 示例：
 * 输入：[3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 思想是：
 * 运用存储下标的单调栈从当前元素向左和向右扩充
 * 向左扩充找到数组比当前元素小的下标，向左扩充的边界条件是值为-1
 * 向右扩充找到数组比当前元素小的下标，向右扩充的边界条件是值为数组长度，其中向右扩充时循环是从数组最后的一个下标开始
 *
 * @since 2020-06-11
 */
public class LeetCode907 {
    public int sumSubarrayMins(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;

        // prev has i* - 1 in increasing order of A[i* - 1]
        // where i* is the answer to query j
        Stack<Integer> stack = new Stack();
        int[] prev = new int[N];
        for (int i = 0; i < N; ++i) {
            while (!stack.isEmpty() && A[i] <= A[stack.peek()]) {
                stack.pop();
            }
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // next has k* + 1 in increasing order of A[k* + 1]
        // where k* is the answer to query j
        stack = new Stack();
        int[] next = new int[N];
        for (int k = N - 1; k >= 0; --k) {
            while (!stack.isEmpty() && A[k] < A[stack.peek()]) {
                stack.pop();
            }
            next[k] = stack.isEmpty() ? N : stack.peek();
            stack.push(k);
        }

        // Use prev/next array to count answer
        long ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += (i - prev[i]) * (next[i] - i) % MOD * A[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;

    }

    public int sumSubarrayMins2(int[] A) {
        int length = A.length;
        int[] left = new int[length];
        int[] right = new int[length];
        left[0] = -1;
        right[length - 1] = length;
        for (int i = 0; i < length; i++) {
            int l = i - 1;
            while (l >= 0 && A[l] > A[i]) {
                l = left[l];
            }
            left[i] = l;
        }
        for (int i = length - 1; i >= 0; i--) {
            int r = i + 1;
            while (r < length && A[r] >= A[i]) {
                r = right[r];
            }
            right[i] = r;
        }
        int ans = 0;
        int mod = 1000000007;
        for (int i = 0; i < length; i++) {
            ans = (ans + (i - left[i]) * (right[i] - i) * A[i]) % mod;
        }
        return ans;
    }
}
