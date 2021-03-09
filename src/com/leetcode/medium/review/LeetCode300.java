package com.leetcode.medium.review;

/*
给定一个无序的整数数组，找到其中最长上升子序列的长度。
 */
public class LeetCode300 {
    public int lengthOfLIS(int[] nums) {
        int[] top = new int[nums.length];
        // 牌堆数初始化为 0
        int piles = 0;
        for (int i = 0; i < nums.length; i++) {
            // 要处理的扑克牌
            int poker = nums[i];
            /***** 搜索左侧边界的⼆分查找 *****/
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (top[mid] > poker) {
                    right = mid;
                } else if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            /*********************************/
            // 没找到合适的牌堆，新建⼀堆
            if (left == piles) piles++;
            // 把这张牌放到牌堆顶
            top[left] = poker;
        }
        // 牌堆数就是 LIS ⻓度
        return piles;
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