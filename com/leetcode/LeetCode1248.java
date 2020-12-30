/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * 请返回这个数组中「优美子数组」的数目。
 *  
 * 示例 1：
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 *
 * @since 2020-06-11
 */
public class LeetCode1248 {
    public int numberOfSubarrays(int[] nums, int k) {
        int len = nums.length;
        int[] oddPos = new int[len + 2]; // 第k个奇数出现的位置
        int pos = 0;
        for (int i = 0; i < len; ++i) {
            if ((nums[i] & 1) == 1) {
                oddPos[++pos] = i;
            }
        }
        oddPos[0] = -1;
        oddPos[pos + 1] = len;

        int res = 0;
        for (int i = 1; i + k < pos + 2; i++) {
            res += (oddPos[i] - oddPos[i - 1]) * (oddPos[i + k] - oddPos[i + k - 1]);
        }

        return res;
    }
}
