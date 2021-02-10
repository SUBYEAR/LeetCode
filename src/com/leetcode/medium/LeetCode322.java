package com.leetcode.medium;

import java.util.Arrays;

public class LeetCode322 {
    public int coinChange(int[] coins, int amount) {
/*        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];
        // base case
        for (int i = 0; i <= n; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++)
                if (j - coins[i - 1] >= 0)
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                else
                    dp[i][j] = dp[i - 1][j];
        }
        for (int[] arr : dp) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
        return dp[n][amount];*/
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
