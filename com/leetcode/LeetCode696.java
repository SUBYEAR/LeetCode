/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述
 *
 * @since 2020-08-10
 */
public class LeetCode696 {
    public int countBinarySubstrings(String s) {
        List<Integer> countArr = new ArrayList<>();
        int i = 0;
        char[] chars = s.toCharArray();
        while (i < s.length()) {
            int count = getContinueCount(chars, i,chars[i]);
            countArr.add(count);
            i += count;
        }
        int res= 0;
        i = 0;
        while (i + 1 < countArr.size()) {
            res += Math.min(countArr.get(i),countArr.get(i + 1));
            i++;
        }
        return res;
    }

    int getContinueCount(char[] arr, int start, char c) {
        int i = 0;
        for (i = start; i < arr.length; i++) {
            if (arr[i] == c) {
                continue;
            } else {
                break;
            }
        }
        return i - start;
    }
}
