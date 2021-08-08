package com.leetcode.hard.dp;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode123 {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        // 这里的k次交易理解为买入时消耗一次操作，卖出不占用交易次数
        int[][][] dp = new int[len][3][2]; // dp[i][k][state] 第i天 最多k次交易的情况下state状态下的最大利润
        // base case
        for (int i = 0; i < len; i++) {
            dp[i][0][1] = Integer.MIN_VALUE; // 不可能存在的状态置为负无穷的
        }
        dp[0][1][1] = dp[0][2][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            for (int k = 1; k <= 2; k++) {
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[len - 1][2][0];
    }
}
