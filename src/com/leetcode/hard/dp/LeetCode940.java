package com.leetcode.hard.dp;

/**
 * 给定一个字符串 S，计算 S 的不同非空子序列的个数。
 *
 * 因为结果可能很大，所以返回答案模 10^9 + 7.
 *
 *  
 *
 * 示例 1：
 *
 * 输入："abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode940 {
    // 回溯会超时，使用动态规划
    private final int MOD = 1_000_000_007;
    public int distinctSubseqII(String S) {
        int n = S.length();
        int[][] dp = new int[n][27]; // dp[i][j] 表示S[0...i]中以字母j(j的取值范围是a~z)结尾的数量
        dp[0][S.charAt(0) - 'a'] = 1;
        dp[0][26] = 1;

        for (int i = 1; i < n; i++) {
            int j = i - 1;
            for (int k = 0; k < 26; k++) {
                dp[i][k] = dp[j][k];
            }

            dp[i][S.charAt(i) - 'a'] = (dp[i - 1][26] + 1) % MOD;
            dp[i][26] = getCount(dp[i]);

        }
        return dp[n - 1][26];
    }

    int getCount(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            ans = (ans + arr[i]) % MOD;
        }
        return ans;
    }
}
