/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 *
 * @since 2020-05-27
 */
public class LeetCode974 {
    public int subarraysDivByK(int[] A, int K) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for (int val : A) {
            sum += val;
            int mod = (sum % K + K) % K; // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int sameMod = map.getOrDefault(mod, 0);
            res += sameMod;
            map.put(mod, sameMod + 1);
        }
        return res;
    }
}
