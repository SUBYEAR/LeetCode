/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 * @since 2020-06-02
 */
public class LeetCode1423 {
    public int maxScore(int[] cardPoints, int k) {
        int sum = 0;
        int i = 0;
        int subArr = 0;
        for (int point : cardPoints) {
            if (i < cardPoints.length - k) {
                subArr += point;
                ++i;
            }
            sum += point;
        }
        int minSum = subArr;
        for (i = 1; i <= k; i++) {
            int temp = subArr - cardPoints[i - 1] + cardPoints[i + cardPoints.length - k - 1];
            minSum = Math.min(minSum, temp);
            subArr = temp;
        }
        return sum - minSum;
    }
}
