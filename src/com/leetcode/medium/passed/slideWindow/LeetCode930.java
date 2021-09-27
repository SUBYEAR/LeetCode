package com.leetcode.medium.passed.slideWindow;

/**
 * 给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
 *
 * 子数组 是数组的一段连续部分。
 */
public class LeetCode930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMostKOne(nums, goal) - atMostKOne(nums, goal - 1);
    }

    private int atMostKOne(int[] nums, int k) {
        if (k < 0) return 0;
        int len = nums.length;
        int[] freq = new int[2]; // nums中的元素是0 和 1
        int left  = 0;
        int right = 0;
        int res = 0;

        while (right < len) {
            ++freq[nums[right]];
            right++;

            while (freq[1] > k) {
                --freq[nums[left]];
                left++;
            }

            res += right - left;
        }
        return res;
    }
}
