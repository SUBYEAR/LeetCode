package com.leetcode.medium.review.dp;

/**
 * 给你一个整数数组 arr 。请你返回和为 奇数 的子数组数目。
 *
 * 由于答案可能会很大，请你将结果对 10^9 + 7 取余后返回。
 */
public class LeetCode1524 {
    public int numOfSubarrays(int[] arr) { // dp解法比前缀和解法更好理解
        int n = arr.length;
        long res = 0L;
        int[][] dp = new int[n][2]; // dp[i]表示数组arr[0...i]上奇数和、偶数和的个数
        if (arr[0] % 2 == 0) {
            dp[0][1] = 1;
        } else {
            dp[0][0] = 1;
            res += 1;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] % 2 != 0) {//当前元素是奇数
                //奇数+偶数=奇数;奇数+奇数=偶数
                dp[i][0] = dp[i - 1][1] + 1;//+1是算上他自己
                dp[i][1] = dp[i - 1][0];
            } else { //当前元素是偶数
                //偶数+奇数=奇数;偶数+偶数=偶数
                dp[i][0] = dp[i - 1][0];
                dp[i][1] = dp[i - 1][1] + 1;//+1是算上他自己
            }
            res += dp[i][0];
        }
        return (int)res % 1000000007;
    }
}

// 前缀和解法
//     public int numOfSubarrays(int[] arr) {
//        final int MODULO = 1000000007;
//        int odd = 0, even = 1;
//        int subarrays = 0;
//        int sum = 0;
//        int length = arr.length;
//        for (int i = 0; i < length; i++) {
//            sum += arr[i];
//            subarrays = (subarrays + (sum % 2 == 0 ? odd : even)) % MODULO;
//            if (sum % 2 == 0) {
//                even++;
//            } else {
//                odd++;
//            }
//        }
//        return subarrays;
//    }
