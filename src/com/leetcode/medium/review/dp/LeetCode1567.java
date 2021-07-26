package com.leetcode.medium.review.dp;

/**
 * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
 *
 * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
 *
 * 请你返回乘积为正数的最长子数组长度。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-subarray-with-positive-product
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1567 {
    public int getMaxLen(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = nums[0] > 0 ? 1 : 0;
        dp[0][1] = nums[0] < 0 ? 1 : 0;
        int res = dp[0][0];
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                dp[i][0] = dp[i][1] = 0;
            } else if (nums[i] > 0) {
                dp[i][0] = dp[i - 1][0] + 1;
                dp[i][1] = dp[i - 1][1] != 0 ? dp[i - 1][1] + 1 : 0;
            } else {
                dp[i][0] = dp[i - 1][1] != 0 ? dp[i - 1][1] + 1 : 0;
                dp[i][1] = dp[i - 1][0] + 1;
            }
            res = Math.max(res, dp[i][0]);
        }

        return res;
    }
}
