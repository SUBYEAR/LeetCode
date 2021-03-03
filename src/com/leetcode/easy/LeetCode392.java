package com.leetcode.easy;

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/is-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode392 {
    public boolean isSubsequence(String s, String t) {
        // 动态规划f[i][j] 表示t字符串中从 i 位置开始 j 字符第一次出现的位置
        int m = t.length(), n = s.length();
        int[][] dp = new int[m + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[m][i] = m;
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a') {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        int next = 0;
        for (int i = 0; i < n; i++) {
            if (dp[next][s.charAt(i) - 'a'] == m) {
                return false;
            }
            next = dp[next][s.charAt(i) - 'a'] + 1;
        }
        return true;
    }
}
