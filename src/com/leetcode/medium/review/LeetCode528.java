/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 给定一个正整数数组 w ，其中 w[i] 代表位置 i 的权重，请写一个函数 pickIndex ，它可以随机地获取位置 i，选取位置 i 的概率与 w[i] 成正比。
 *
 * 例如，给定一个值 [1，9] 的输入列表，当我们从中选择一个数字时，很有可能 10 次中有 9 次应该选择数字 9 作为答案。
 *
 * 前缀和与二分搜索
 * tot 为前i个数的和
 *
 * 在范围 [0,tot) 中随机选择一个整数 targ。
 *
 * 使用二分查找来找到下标 x，其中 x 是满足 targ<p[x] 的最小下标。
 *
 * 对于某些下标 i，所有满足 p[i]−w[i]≤v<p[i] 的整数 v 都映射到这个下标。因此，所有的下标都与下标权重成比例。
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
