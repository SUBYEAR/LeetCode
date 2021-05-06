package com.leetcode.medium.review;

/**
 * 给你一个整数 n ，请你找出并返回第 n 个 丑数 。丑数 就是只包含质因数 2、3 和/或 5 的正整数。
 */
public class LeetCode264 {
    public int nthUglyNumber(int n) {
        int[] res = new int[1690];
        res[0] = 1;
        int ugly = 0, i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < 1690; i++) {
            ugly = Math.min(res[i2] * 2, Math.min(res[i3] * 3, res[i5] * 5));
            res[i] = ugly;

            if (ugly == res[i2] * 2) i2++;
            if (ugly == res[i3] * 3) i3++;
            if (ugly == res[i5] * 5) i5++;
        }
        return res[n - 1];
    }
}
