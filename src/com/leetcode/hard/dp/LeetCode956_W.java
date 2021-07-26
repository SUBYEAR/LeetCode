package com.leetcode.hard.dp;

import java.util.Arrays;

/**
 * 你正在安装一个广告牌，并希望它高度最大。这块广告牌将有两个钢制支架，两边各一个。每个钢支架的高度必须相等。
 *
 * 你有一堆可以焊接在一起的钢筋 rods。举个例子，如果钢筋的长度为 1、2 和 3，则可以将它们焊接在一起形成长度为 6 的支架。
 *
 * 返回广告牌的最大可能安装高度。如果没法安装广告牌，请返回 0。
 *
 *  
 * 提示：
 *
 * 0 <= rods.length <= 20
 * 1 <= rods[i] <= 1000
 * 钢筋的长度总和最多为 5000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/tallest-billboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode956_W {
    int NINF = Integer.MAX_VALUE / 3;
    Integer[][] memo;

    // 对每一根长度是 x 钢筋将其计算到结果中时其状态是 x(累加到当前这一侧), 0(不选), -x(累加到另一侧)
    // 目标是最终得到结果为0并且正数之和最大，记所有的正数之和为score
    public int tallestBillboard(int[] rods) {
        // dp[i][s]表示之前选择的数字和为s(不统计在score内)的情况下，使用数组rods[i...]时能得到的正数和score的值最大
        int N = rods.length;
        memo = new Integer[N][100001];
        return (int) dp(rods, 0, 5000); // 之前选择的数字之和为0时，数组rods从0开始得到的最大score值
    }

    public int dp(int[] rods, int i, int s) {
        if (i == rods.length) {
            return  s == 5000 ? 0 : NINF; // 边界情况当s为0时dp[rods.length][s]=0，其他情况NINF
        } else if (memo[i][s] != null) {
            return memo[i][s];
        } else {
            int ans = dp(rods, i + 1, s); // 不选
            ans = Math.max(ans, dp(rods, i + 1, s - rods[i])); // x:选在当前侧，那么之前选择的数字和从s倒推为是s - rods[i]
            ans = Math.max(ans, rods[i] + dp(rods, i + 1, s + rods[i])); // -x:选在另一侧，那么之前选择的数字和从s倒推为是s + rods[i]
            memo[i][s] = ans;
            return ans;
        }
    }

    public int tallestBillboard_dp(int[] rods) {
        int len = rods.length;
        if (len < 2) {
            return 0;
        }
        int sum = Arrays.stream(rods).sum();
        // 定义状态方程：dp[i][j]: 表示用前i+1个钢筋，形成两个互斥子集合的差为j，子集合的和最大
        // 例如【1，2，3，6】, 我们用前4个钢筋，差为1的两个子集合有很多，dp[3][1], 如{1}和{2}, {2}和{3}
        // {2,3}和{6}, 最大和的两个子集合，应该是{2,3}和{6}, 它们的和为11， 所以dp[3][1] = 11;
        // 我们的目标是找到dp[n][0]的值。
        int[][] dp = new int[len][sum + 1];
        dp[0][rods[0]] = rods[0];
        int preSum = rods[0];
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < preSum; j++) {
                if (dp[i - 1][j] < j) {
                    // 状态数组里有一些无效的值，如果两个子集的差为j, 它们的和肯定大于j。 这种dp值是无效的，不应该
                    // 用来更新dp[i]的值
                    continue;
                }

                // 更新dp[i][j], 有3种情况；
                // 1. 元素i不放入任一一个子集合， 那么差与和都不更新
                dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);

                // 2. 元素i放入和更大的子集合，那么子集合差就会更大，我们应该更新dp[i][j+rods[i]]
                int k = j + rods[i];
                dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + rods[i]);

                // 3. 元素i放入和更小的子集合，那么子集合的差就会变小, 我们应该更新dp[i][j-rods[i]]
                k = Math.abs(j - rods[i]);
                dp[i][k] = Math.max(dp[i][k], dp[i - 1][j] + rods[i]);
            }

            preSum += rods[i];
        }
        return dp[len - 1][0] / 2;
    }

    // https://leetcode-cn.com/problems/tallest-billboard/solution/dong-tai-gui-hua-ke-yi-kan-cheng-01bei-bao-de-bian/
}
