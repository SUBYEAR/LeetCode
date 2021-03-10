/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

/**
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * @since 2020-06-23
 */
public class LeetCode96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            int subTree = 0;
            for (int j = 1; j < i - 1; j++) {
                subTree += dp[j] * dp[i - 1 - j];
            }
            dp[i] = dp[i - 1] + subTree + dp[i - 1]; // 这三个值分别代表只有左子树, 左右子树都有, 只有右子树的情况
        }
        return dp[n];
    }

    public int numTrees_Au(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
