/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.dp;

import java.util.*;

/**
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 * 说明：
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 *
 * 定义 dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i-1]是否能被空格拆分成若干个字典中出现的单词
 *
 * @since 2020-06-30
 */
public class LeetCode139 {
    int maxLength = 0;

    int minLength = 0;

    public boolean wordBreak(String s, List<String> wordDict) {
        Map<String, Integer> map = new HashMap<>();
        int dp[] = new int[s.length()];
        Arrays.fill(dp, -1);
        for (int i = 0; i < wordDict.size(); i++) {
            maxLength = Math.max(maxLength, wordDict.get(i).length());
            minLength = Math.min(minLength, wordDict.get(i).length());
            map.put(wordDict.get(i), 1);
        }

        return searchStr(s, map, dp, 0);
    }

    private boolean searchStr(String s, Map<String, Integer> map, int[] dp, int startIndex) {
        if (startIndex == s.length()) {
            return true;
        }
        if (dp[startIndex] != -1) {
            return dp[startIndex] == 1;
        }

        for (int i = startIndex + 1, length = Math.min(startIndex + maxLength, s.length()); i <= length; i++) {
            if (i - startIndex < minLength) {
                continue;
            }
            if (map.containsKey(s.substring(startIndex, i))) {
                if (searchStr(s, map, dp, i)) {
                    dp[startIndex] = 1;
                    return true;
                }
            }
        }
        dp[startIndex] = 0;
        return false;
    }

    public boolean wordBreak_DP(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
