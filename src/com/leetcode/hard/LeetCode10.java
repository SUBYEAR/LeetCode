/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.hard;

/**
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 *
 * @since 2020-06-20
 */
public class LeetCode10 {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2]; // 不匹配字符，将该组合扔掉，不再进行匹配
                    if (matches(s, p, i, j - 1)) { // 匹配 s 末尾的一个字符，将该字符扔掉，而该组合还可以继续进行匹配；
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }

    public boolean isMatch2(String s, String p) {
        /*
         * s和p可能为空。空的长度就是0，但是这些情况都已经判断过了，只需要判断是否为null即可
         * if(p.length()==0&&s.length()==0)
         * return true;
         */
        if (s == null || p == null) {
            return false;
        }
        int rows = s.length();
        int columns = p.length();
        boolean[][] dp = new boolean[rows + 1][columns + 1];
        // s和p两个都为空，肯定是可以匹配的，同时这里取true的原因是
        // 当s=a，p=a，那么dp[1][1] = dp[0][0]。因此dp[0][0]必须为true。
        dp[0][0] = true;
        for (int j = 1; j <= columns; j++) {
            // p[j-1]为*可以把j-2和j-1处的字符删去，只有[0,j-3]都为true才可以
            // 因此dp[j-2]也要为true，才可以说明前j个为true
            if (p.charAt(j - 1) == '*' && dp[0][j - 2]) {
                dp[0][j] = true;
            }
        }

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                char nows = s.charAt(i - 1);
                char nowp = p.charAt(j - 1);
                if (nows == nowp) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    if (nowp == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (nowp == '*') {
                        // p需要能前移1个。（当前p指向的是j-1，前移1位就是j-2，因此为j>=2）
                        if (j >= 2) {
                            char nowpLast = p.charAt(j - 2);
                            // 只有p[j-2]==s[i-1]或p[j-2]==‘.’才可以让*取1个或者多个字符：
                            if (nowpLast == nows || nowpLast == '.') {
                                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                            }
                            // 不论p[j-2]是否等于s[i-1]都可以删除掉j-1和j-2处字符：
                            dp[i][j] = dp[i][j] || dp[i][j - 2];
                        }
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }
        return dp[rows][columns];
    }
}
