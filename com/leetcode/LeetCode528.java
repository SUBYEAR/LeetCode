/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 *
 * 例如，给定一个值 [1，9] 的输入列表，当我们从中选择一个数字时，很有可能 10 次中有 9 次应该选择数字 9 作为答案。
 * 前缀和与二分搜索
 * 想法让 {tot} = \sum_{i=0}^{N-1}w[i]tot=∑
 * i=0
 * N−1
 * ​
 *  w[i] ，其中 N = \text{len}(w)N=len(w)。
 *
 * 如果我们从 半开区间 [0, \text{tot})[0,tot) 中随机选择一个整数会发生什么？
 *
 * 是否有办法将每一个可能的整数映射到 ww 中一个下标，使得每个下标映射的数目与下标的权重对应呢？
 *
 * 是否有办法使用少于 O(\text{tot})O(tot) 的空间呢？
 *
 * 算法
 *
 * 求出前缀和数组 pp，其中 p[x] = \sum_{i=0}^{x}w[i]p[x]=∑
 * i=0
 * x
 * ​
 *  w[i]。
 *
 * 在范围 [0,tot) 中随机选择一个整数 targ。
 *
 * 使用二分查找来找到下标 xx，其中 xx 是满足 \text{targ} < p[x]targ<p[x] 的最小下标。
 *
 * 对于某些下标 ii，所有满足 p[i] - w[i] \leq v < p[i]p[i]−w[i]≤v<p[i] 的整数 vv 都映射到这个下标。因此，所有的下标都与下标权重成比例。
 *
 * @since 2020-07-06
 */
public class LeetCode528 {
    List<Integer> psum = new ArrayList<>();
    int tot = 0;
    Random rand = new Random();

    public LeetCode528(int[] w) {
        for (int x : w) {
            tot += x;
            psum.add(tot);
        }
    }

    public int pickIndex() { // 左边界的二分查找 当target不存在时，得到的索引是恰好比val大的最小元素的索引
        int targ = rand.nextInt(tot);

        int lo = 0;
        int hi = psum.size() - 1;
        while (lo != hi) {
            int mid = (lo + hi) / 2;
            if (targ >= psum.get(mid)) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }
}
