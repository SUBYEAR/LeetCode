/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

/**
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 *
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 *
 * 返回获得利润的最大值。
 *
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2020-12-17
 */
public class LeetCode714 {
    public int maxProfit(int[] prices, int fee) {
        // dp[i][k][state] k是无穷大
        // dp[i][k][0] = Max(dp[i - 1][k][0], dp[i - 1][k][1] + p[i] - fee)
        // dp[i][k][1] = Max(dp[i - 1][k][1], dp[i - 1][k-1][0] - p[i])
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i -1][1] - fee + prices[i]);
            System.out.println("Day " + i + ", Unhold Benefit: " + dp[i][0]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i -1][0] - prices[i]);
            System.out.println("Day " + i + ", Hold Benefit: " + dp[i][1]);
        }

        return dp[length - 1][0];
    }
}
