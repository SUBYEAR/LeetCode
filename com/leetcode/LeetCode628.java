package com.leetcode;

import java.util.Arrays;

public class LeetCode628 {
    public int maximumProduct(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        int res = nums[length - 1] * nums[length - 2] * nums[length - 3];
        if (nums[0] > 0 || nums[length - 1] < 0) {
            return res;
        }

        int tmp = nums[0] * nums[1] * nums[length - 1];
        res = Math.max(tmp, res);
        return res;
    }
}
