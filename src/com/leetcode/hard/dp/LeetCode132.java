package com.leetcode.hard.dp;

import java.util.Arrays;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。
 *
 * 返回符合要求的 最少分割次数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：1
 * 解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：0
 * 示例 3：
 *
 * 输入：s = "ab"
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode132 {
    public int minCut(String s) {
        int len = s.length();
        int[] dp = new int[len];
        Arrays.fill(dp, len);
        dp[0] = 0;
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (isPalindrome(s, j, i)) {
                    dp[i] = Math.min(dp[i], j > 0 ? dp[j - 1] + 1 : 0);
                }
            }

            dp[i] = Math.min(dp[i], dp[i - 1] + 1);

        }

        return dp[len - 1];
    }

    boolean isPalindrome(String s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}

// 官方题解
// public int minCut(String s) {
//    int n = s.length();
//        boolean[][] g = new boolean[n][n];
//        for (int i = 0; i < n; ++i) {
//            Arrays.fill(g[i], true);
//        }
//
//        for (int i = n - 1; i >= 0; --i) {
//            for (int j = i + 1; j < n; ++j) {
//                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
//            }
//        }
//
//        int[] f = new int[n];
//        Arrays.fill(f, Integer.MAX_VALUE);
//        for (int i = 0; i < n; ++i) {
//            if (g[0][i]) {
//                f[i] = 0;
//            } else {
//                for (int j = 0; j < i; ++j) {
//                    if (g[j + 1][i]) {
//                        f[i] = Math.min(f[i], f[j] + 1);
//                    }
//                }
//            }
//        }
//
//        return f[n - 1];
//    }
