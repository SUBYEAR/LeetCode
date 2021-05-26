package com.leetcode.hard.dp;

/**
 *给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 *
 * 注该题是通配符匹配不是正则表达式匹配
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode44 {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        if (n == 0) return m == 0;

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1]; // 匹配空串
                    if (i > 0) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else {
                    if (isCharMatch(s, p, i, j)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    boolean isCharMatch(String s, String p, int i, int j) {
        if (i == 0) return false;

        if (p.charAt(j - 1) == '?') return true;

        return s.charAt(i - 1) == p.charAt(j - 1);
    }
}

// 官方解法
//     public boolean isMatch(String s, String p) {
//        int m = s.length();
//        int n = p.length();
//        boolean[][] dp = new boolean[m + 1][n + 1];
//        dp[0][0] = true;
//        for (int i = 1; i <= n; ++i) {
//            if (p.charAt(i - 1) == '*') {
//                dp[0][i] = true;
//            } else {
//                break;
//            }
//        }
//        for (int i = 1; i <= m; ++i) {
//            for (int j = 1; j <= n; ++j) {
//                if (p.charAt(j - 1) == '*') {
//                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
//                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                }
//            }
//        }
//        return dp[m][n];
//    }
