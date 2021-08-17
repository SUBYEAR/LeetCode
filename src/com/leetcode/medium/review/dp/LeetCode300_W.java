package com.leetcode.medium.review.dp;

/**
给定一个无序的整数数组，找到其中最长上升子序列的长度。
 */
public class LeetCode300_W {
    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) { // 最后的一堆排的最上面的值是最大值
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}

// 动态规划解法
// public int lengthOfLIS(int[] nums) {
//        if (nums.length == 0) {
//            return 0;
//        }
//        int[] dp = new int[nums.length];
//        dp[0] = 1;
//        int maxans = 1;
//        for (int i = 1; i < nums.length; i++) {
//            dp[i] = 1;
//            for (int j = 0; j < i; j++) {
//                if (nums[i] > nums[j]) {
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//            maxans = Math.max(maxans, dp[i]);
//        }
//        return maxans;
//    }
//