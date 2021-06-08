package com.leetcode.medium.review.dp;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode474 {
    public int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        // 其中 dp[i][j][k] 表示在前 i 个字符串中，使用 j 个 0 和 k 个 1 的情况下最多可以得到的字符串数量。
        int[][][] dp = new int[length + 1][m + 1][n + 1];

        for (int i = 1; i <= length; i++) {
            int[] zeroOne = getZerosOnes(strs[i - 1]);
            int zeros = zeroOne[0], ones = zeroOne[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeroOne[0] && k >= zeroOne[1]) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);

                    }
                }
            }
        }
        return dp[length][m][n];
    }

    int[] getZerosOnes(String s) {
        int[] ans = new int[2];
        for (char c : s.toCharArray()) {
            ans[c - '0']++;
        }
        return ans;
    }
}
