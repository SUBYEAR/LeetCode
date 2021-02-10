/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组，并返回其长度。如果不存在符合条件的连续子数组，返回 0。
 *
 * @since 2020-06-27
 */
public class LeetCode209 {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        int sum = 0;
        int res = len + 1;
        int left = 0;
        int right = 0;
        for (; right < len; right++) {
            sum += nums[right];
            if (sum < s) {
                continue;
            }

            while (sum >= s) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                ++left;
            }
        }
        return res;
    }
}
