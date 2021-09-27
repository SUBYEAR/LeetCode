package com.leetcode.medium.passed.binarySearch;

/**
 * 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，找出这个数。
 */
public class LeetCode540 {
    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i += 2) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            }
        }
        return nums[len - 1];
    }

    // 偶数索引进行二分搜索
    public int singleNonDuplicate_bs(int[] nums) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (mid % 2 == 1) {
                mid--;
            }
            if (nums[mid] == nums[mid + 1]) {
                lo = mid + 2;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
