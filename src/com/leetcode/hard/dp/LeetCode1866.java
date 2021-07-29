package com.leetcode.hard.dp;

/**
 * 有 n 根长度互不相同的木棍，长度为从 1 到 n 的整数。请你将这些木棍排成一排，并满足从左侧 可以看到 恰好 k 根木棍。
 * 从左侧 可以看到 木棍的前提是这个木棍的 左侧 不存在比它 更长的 木棍。
 *
 * 例如，如果木棍排列为 [1,3,2,5,4] ，那么从左侧可以看到的就是长度分别为 1、3 、5 的木棍。
 * 给你 n 和 k ，返回符合题目要求的排列 数目 。由于答案可能很大，请返回对 109 + 7 取余 的结果。
 * 提示：
 *
 * 1 <= n <= 1000
 * 1 <= k <= n
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-rearrange-sticks-with-k-sticks-visible
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1866 {
    public int rearrangeSticks(int n, int k) {
        final int MOD = 1000_000_007;
        int[][] dp = new int[n + 1][k + 1]; // dp[i][k] 使用1~i的数，满足从左侧看到恰好k根木棍
        dp[0][0] = 1;
        // 状态转移时考虑最后一根木棍是否可以看到
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(k, i); j++) {
                long tmp = dp[i - 1][j - 1]; // 最后一根木棍可以被看到，那么这跟木棍只能是长度为i的木棍
                // 最后一根木棍不能被看到时有i- 1个选择，前面i - 1根木棍 那么任意两根木棍的「相对高度关系」都保持不变，即我们仍然可以看到 j 根木棍
                tmp = (tmp + (long) (i - 1) * dp[i - 1][j] % MOD) % MOD;
                dp[i][j] = (int) tmp;
            }
        }

        return dp[n][k];
    }
}
