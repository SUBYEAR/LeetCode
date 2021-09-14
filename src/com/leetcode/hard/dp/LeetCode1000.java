package com.leetcode.hard.dp;

/**
 * 有 N 堆石头排成一排，第 i 堆中有 stones[i] 块石头。
 *
 * 每次移动（move）需要将连续的 K 堆石头合并为一堆，而这个移动的成本为这 K 堆石头的总数。
 *
 * 找出把所有石头合并成一堆的最低成本。如果不可能，返回 -1 。
 * 提示：
 *
 * 1 <= stones.length <= 30
 * 2 <= K <= 30
 * 1 <= stones[i] <= 100
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1000 {
    // 不用Integer.MAX_VALUE,因为Integer.MAX_VALUE + 正数 会溢出变为负数
    private int MAX_VALUE = 99999999;
    public int mergeStones(int[] stones, int k) {
        // 定义dp[i][j][k]为合并第i堆到第j堆石头为k堆的成本，状态转移方程有关键两点：
        // dp[i][j][1] = dp[i][j][k] + sum(i, j)。不能直接求出合并为1堆的成本，得先合并成k堆。
        // dp[i][j][m] = min(dp[i][p][1] + dp[p + 1][j][m - 1])，i <= p < j，2 <= m <= k。这里m为堆数，
        // 不能直接用k是因为右部分的存在，要对右部分继续划分求解的话，子问题就必须有合并成m堆的情况。
        // 初始化dp[i][i][1] = 0，答案就是dp[1][n][1]。对于无法实现的情况，定义dp[i][j][k] = max。
        int n = stones.length;
        if ((n - 1) % (k - 1) != 0) return -1;
        int[][][] dp = new int[n + 1][n + 1][k + 1];
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; ++i) sum[i] = sum[i - 1] + stones[i - 1];
        for (int i = 1; i <= n; ++i) {
            for (int j = i; j <= n; ++j) {
                for (int m = 2; m <= k; ++m) dp[i][j][m] = MAX_VALUE;
            }
            dp[i][i][1] = 0;
        }
        for (int len = 2; len <= n; ++len) { // 枚举区间长度
            for (int i = 1; i + len - 1 <= n; ++i) { // 枚举区间起点
                int j = i + len - 1;
                for (int m = 2; m <= k; ++m) { // 枚举堆数
                    for (int p = i; p < j; p += k - 1) {  // 枚举分界点
                        dp[i][j][m] = Math.min(dp[i][j][m], dp[i][p][1] + dp[p + 1][j][m - 1]);
                    }
                }
                dp[i][j][1] = dp[i][j][k] + sum[j] - sum[i - 1];
            }
        }
        return dp[1][n][1];

//        链接：https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/solution/yi-dong-you-yi-dao-nan-yi-bu-bu-shuo-ming-si-lu-he/
    }
}
