/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.hard;

/**
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 *
 * ，对于一个长度为 N 的数组，其中没有出现的最小正整数只能在 [1, N+1]中。这是因为如果 [1, N]
 * 都出现了，那么答案是 N+1，否则答案是 [1, N]中没有出现的最小正整数
 *
 *
 * @since 2020-06-27
 */
public class LeetCode41 {
    public int firstMissingPositive(int[] nums) {
        int len = nums.length;

        // 把不在 [1, N][范围内的数修改成任意一个大于 N的数（例如 N+1）这样一来，数组中的所有数就都是正数了
        for (int i = 0; i < len; i++) {
            if (nums[i] <= 0) {
                nums[i] = len + 1;
            }
        }
// 我们遍历数组中的每一个数 x，它可能已经被打了标记，因此原本对应的数为 |x|。如果∣x∣∈[1,N]，那么我们给数组中的
// 第∣x∣−1 个位置的数添加一个负号
        for (int i = 0; i < len; i++) {
            int flag = Math.abs(nums[i]);
            if (flag <= len) {
                nums[flag - 1] = -Math.abs(nums[flag - 1]);
            }
        }
        // 在遍历完成之后，如果数组中的每一个数都是负数，那么答案是 N+1，否则答案是第一个正数的位置加 1
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return len + 1;
    }
}
