package com.leetcode.medium.review.dp;

/**
 * 给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。
 *
 * 子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode516 {
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len + 1]; // dp[i][len] 表示i开始长度为len的字符串的回文子序列长度
        for (int i = 0; i < len; i++) {
            dp[i][1] = 1;
        }

        for (int k = 2; k <= len; k++) {
            for (int i = len - k; i >= 0; i--) {
                dp[i][k] = Math.max(dp[i][k - 1], dp[i + 1][k - 1]);
                if (s.charAt(i) == s.charAt(i + k - 1)) {
                    dp[i][k] = Math.max(dp[i][k], dp[i + 1][k - 2] + 2);
                }
            }
        }
        return dp[0][len];
    }
}
