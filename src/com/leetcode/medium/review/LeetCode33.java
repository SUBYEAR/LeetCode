package com.leetcode.medium.review;

public class LeetCode33 {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            if (target == nums[mid]) {
                return mid;
            }
            if (nums[0] < nums[mid]) {
                if (target < nums[mid] && nums[0] <= target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
