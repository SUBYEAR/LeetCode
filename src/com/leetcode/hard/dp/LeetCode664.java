package com.leetcode.hard.dp;

/**
 * 有台奇怪的打印机有以下两个特殊要求：
 *
 * 打印机每次只能打印由 同一个字符 组成的序列。
 * 每次可以在任意起始和结束位置打印新字符，并且会覆盖掉原来已有的字符。
 * 给你一个字符串 s ，你的任务是计算这个打印机打印它需要的最少打印次数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strange-printer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode664 {
    // f[i][j] 的计算需要用到 f[i][k] 和 f[k+1][j]其中 i≤k<j。为了保证动态规划的计算过程满足
    // 无后效性，在实际代码中，我们需要改变动态规划的计算顺序，从大到小地枚举 i，并从小到大地枚举 j，这样可以保证当计算 f[i][j] 时，
    // f[i][k] 和 f[k+1][j]都已经被计算过
    // 区间两端的字符相同，那么当我们打印左侧字符 s[i] 时，可以顺便打印右侧字符 s[j];区间两端的字符不同，那么我们需要分别完成该区间的左右两部分的打印

    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    int minn = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        minn = Math.min(minn, dp[i][k] + dp[k + 1][j]);
                    }
                    dp[i][j] = minn;
                }
            }
        }
        return dp[0][n - 1];
    }
}
