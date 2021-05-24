package com.leetcode.medium.review.dp;

/**
 * 我们将给定的数组 A 分成 K 个相邻的非空子数组 ，我们的分数由每个子数组内的平均值的总和构成。计算我们所能得到的最大分数是多少。
 *
 * 注意我们必须使用 A 数组中的每一个数进行分组，并且分数不一定需要是整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-sum-of-averages
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode813 {
    public double largestSumOfAverages(int[] nums, int K) {
        int N = nums.length;
        double[] preSum = new double[N + 1];
        for (int i = 0; i < N; ++i) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        // 设 dp(i, k) 表示将数组 A 中的前 i 个元素 A[:i] 分成 k 个相邻的非空子数组，可以得到的最大分数。dp(i, k) 的值可以
        // 通过 dp(j, k - 1) 转移而来，其中 j < i，状态转移方程为：
        // dp(i, k) = max(dp(j, k - 1) + average(j + 1, i))
        // dp(i, 0) = average(0, i) 边界条件好怪
        // 通过滚动数组的优化方式，最终推导出，从后往前
        // 设 dp(i, k) 表示数组 A 中从第 i 个元素到结尾 A[i:] 分成 k 个相邻的非空子数组，可以得到的最大分数，那么状态转移方程将变为：
        // dp(i, k) = max(dp(j, k - 1) + average(i, j - 1))
        // dp(i, 0) = average(i, n - 1)
        double[] dp = new double[N];
        for (int i = 0; i < N; ++i) {
            dp[i] = (preSum[N] - preSum[i]) / (N - i);
        }

        for (int k = 0; k < K - 1; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = i + 1; j < N; ++j) {
                    dp[i] = Math.max(dp[i], (preSum[j] - preSum[i]) / (j - i) + dp[j]);
                }
            }
        }

        return dp[0];
    }
}
