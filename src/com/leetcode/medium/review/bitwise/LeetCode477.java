package com.leetcode.medium.review.bitwise;

/**
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 *
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 */
public class LeetCode477 {
    // 若长度为 n 的数组 nums 的所有元素二进制的第 i 位共有 c 个 1，n-c 个 0，则些元素在二进制的第 i 位上的汉明距离之和为c * (n - c)
    public int totalHammingDistance(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < 30; ++i) {
            int c = 0;
            for (int val : nums) {
                c += (val >> i) & 1;
            }
            ans += c * (n - c);
        }
        return ans;
    }
}
