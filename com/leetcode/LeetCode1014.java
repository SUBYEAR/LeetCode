/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.huawei.sort;

/**
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * 返回一对观光景点能取得的最高分
 * 得分公式，我们可以将其拆分成 A[i]+i 和 A[j]-j 两部分，这样对于统计景点 j 答案的时候，由于 A[j]-j 是固定不变的，因此
 * 最大化 A[i]+i+A[j]-j的值其实就等价于求 [0,j-1] 中 A[i]+i 的最大值 mx
 *
 * @since 2020-06-17
 */
public class LeetCode1014 {
    public int maxScoreSightseeingPair(int[] A) {
        int ans = 0, mx = A[0] + 0;
        for (int j = 1; j < A.length; ++j) {
            ans = Math.max(ans, mx + A[j] - j);
            // 边遍历边维护
            mx = Math.max(mx, A[j] + j);
        }
        return ans;
    }
}
