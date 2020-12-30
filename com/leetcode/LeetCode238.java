/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;

/**
 * 给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
 *
 * @since 2020-06-04
 */
public class LeetCode238 {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int[] res = new int[nums.length];
        ArrayList<Integer> arr = new ArrayList<>();
        for (int num : nums) {
            arr.add(num);
        }
        for (int i = 0; i < nums.length; i++) {
            arr.remove(i);
            res[i] = product(arr.toArray(new Integer[0]), 0, arr.size() - 1);
            arr.add(i, nums[i]);
        }

        return res;
    }

    public int product(Integer[] list, int start, int end) {
        if (start == end) {
            return list[start];
        } else if (start > end) {
            return 1;
        } else if (list[end] == 0 || list[start] == 0) {
            return 0;
        }

        return list[end] * list[start] * product(list, start + 1, end - 1);
    }
}
