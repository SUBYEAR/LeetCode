package com.leetcode.easy;

/**
 * 给定一个二进制数组， 计算其中最大连续 1 的个数。
 */
public class LeetCode485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 1) {
                right++;
                res = Math.max(res, right - left);
            } else {
                right++;
                left = right;
            }
        }
        return res;
    }
}
