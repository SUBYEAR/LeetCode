/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。
 * 对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。
 *
 * @since 2020-06-01
 */
public class LeetCode1431 {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> res = new LinkedList<>();
        if (candies == null) {
            return res;
        }

        int max = 0;
        for (int n : candies) {
            max = Math.max(max, n);
        }

        for (int n : candies) {
            if (max - n > extraCandies) {
                res.add(Boolean.FALSE);
            } else {
                res.add(Boolean.TRUE);
            }
        }
        return res;
    }
}
