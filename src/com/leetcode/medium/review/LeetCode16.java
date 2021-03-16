/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。
 * 找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 双指针找到下一个不一样的数来更新结果
 * @since 2020-06-24
 */
public class LeetCode16 {
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        Arrays.sort(nums);
        int res = 10000000;
        for (int first = 0; first < len; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int second = first + 1;
            int third = len - 1;
            while (second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(sum - target) < Math.abs(res - target)) {
                    res = sum;
                }
                if (sum > target) {
                    int temp = third - 1;
                    while (second < temp && nums[temp] == nums[third]) {
                        --temp;
                    }
                    third = temp;
                } else {
                    int temp = second + 1;
                    while (temp < third && nums[temp] == nums[second]) {
                        ++temp;
                    }
                    second = temp;
                }
            }
        }
        return res;
    }
}

// 可考虑使用回溯算法，可能会超时，需要剪枝