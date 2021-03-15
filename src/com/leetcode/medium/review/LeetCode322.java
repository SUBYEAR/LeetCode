package com.leetcode.medium.review;

import java.util.Arrays;

/**
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
public class LeetCode322 {
    public int coinChange(int[] coins, int amount) {
// F(i) 为组成金额 i 所需最少的硬币数量
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0; // base case
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i- coin]);
            }
        }
        return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
}
