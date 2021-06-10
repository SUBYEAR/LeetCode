package com.leetcode.hard.dp;

/**
 * 集团里有 n 名员工，他们可以完成各种各样的工作创造利润。
 *
 * 第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。
 *
 * 工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。
 *
 * 有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/profitable-schemes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode879 {
    // 本题与经典背包问题非常相似。两者不同点在于经典背包问题只有一种容量限制，
    // 而本题却有两种限制：集团员工人数上限 n，以及工作产生的利润下限 inProfit
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        // 三维数组 dp 作为动态规划的状态，其中 dp[i][j][k] 表示在前 i 个工作中选择了 j 个员工，并且满足工作利润【至少】为 k 的情况下的盈利计划的总数目
        int len = group.length, MOD = (int)1e9 + 7;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= minProfit; k++) {
                    if (j < members) {
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        // 在前i-1个中选取j个人开展工作 + 在前i - 1个人中选取j-group[i]人开展工作，剩下的人开展第i个工作
                        // 右侧的第三维是 max(0,k−profit[i]) 而不是 k−profit[i],这样巧妙的处理了k−profit[i]为负数的情况
                        //  (1) profit[i-1]<=k的时候，前i-1种工作中选择的利润至少要达到k-profit[i-1]
                        //  (2) 当profit[i-1]>k的时候，说明只做第i种工作就能满足条件，前i-1种工作的利润只要满足>=0就可以
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) { // 因为第三维是至少为k，所以这里可以直接相加，不然还需要循环遍历第三维度
            sum = (sum + dp[len][i][minProfit]) % MOD;
        }
        return sum;
    }
}
