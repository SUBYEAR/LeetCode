/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 最长回文子串
 *
 * @since 2020-06-11
 */
public class LeetCode5 {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public String longestPalindromeDp(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int[][] dp = new int[n][n];
        String ans = new String();
        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = 1;
                } else if (l == 1) {
                    dp[i][j] = (arr[i] == arr[j]) ? 1 : 0;
                } else {
                    dp[i][j] = (arr[i] == arr[j] && dp[i + 1][j - 1] != 0) ? 1 : 0;
                }
                if (dp[i][j] != 0 && ((l + 1) > ans.length())) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
}
