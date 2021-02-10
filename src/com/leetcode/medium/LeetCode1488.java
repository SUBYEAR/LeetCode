/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 你的国家有无数个湖泊，所有湖泊一开始都是空的。当第 n 个湖泊下雨的时候，如果第 n 个湖泊是空的，那么它就会装满水，否则这个湖泊会发生洪水。你的目标是避免任意一个湖泊发生洪水。
 *
 * 给你一个整数数组 rains ，其中：
 *
 * rains[i] > 0 表示第 i 天时，第 rains[i] 个湖泊会下雨。
 * rains[i] == 0 表示第 i 天没有湖泊会下雨，你可以选择 一个 湖泊并 抽干 这个湖泊的水。
 * 请返回一个数组 ans ，满足：
 *
 * ans.length == rains.length
 * 如果 rains[i] > 0 ，那么ans[i] == -1 。
 * 如果 rains[i] == 0 ，ans[i] 是你第 i 天选择抽干的湖泊。
 * 如果有多种可行解，请返回它们中的 任意一个 。如果没办法阻止洪水，请返回一个 空的数组 。
 *
 * 请注意，如果你选择抽干一个装满水的湖泊，它会变成一个空的湖泊。但如果你选择抽干一个空的湖泊，那么将无事发生（详情请看示例 4）。
 *
 * @since 2020-08-08
 */
public class LeetCode1488 {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] res = new int[n];
        ArrayList<Integer> zeros = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>(); // 记录下雨的水池->下雨的日子
        for (int i = 0; i < n; i++) {
            if (rains[i] == 0) {
                zeros.add(i); // 记录不下雨的日子
                continue;
            }
            res[i] = -1;
            int pool = rains[i];
            if (map.containsKey(pool)) {
                int lastFull = map.get(pool);
                int id = lower_bound(zeros, lastFull); // 前一个满了的pool右边的第一个0的index
                if (id >= zeros.size()) return new int[0];

                res[zeros.get(id)] = pool;
                zeros.remove(id);
            }
            map.put(pool, i);
        }

        for (int i : zeros) res[i] = 1; // 根据题意，把所有没有用到的0置为任意正数
        return res;
    }

    /**二分搜索**/
    private int lower_bound(ArrayList<Integer> A, int target) {
        int lo = 0, hi = A.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (A.get(mid) >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
