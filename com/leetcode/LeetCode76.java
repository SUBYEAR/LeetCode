/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 *
 * 注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2020-12-04
 */
public class LeetCode76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        int valid = 0;
        for (char ch : t.toCharArray()) {
            Integer num = need.getOrDefault(ch, 0);
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }
        int start = 0;
        int len = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            ++right;
            if (need.containsKey(c)) {
                window.put(c,window.getOrDefault(c, 0) + 1);
                int num1 = window.get(c);
                int num2 = need.get(c);
                if (num1 == num2)
                    ++valid;
            }

            // 判断左侧窗口是否要收缩
            while (valid == need.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char remove = s.charAt(left);
                left++;
                if (need.containsKey(remove)) {
                    int num1 = window.get(remove);
                    int num2 = need.get(remove);
                    if (num1 == num2) {
                        --valid;
                    }
                    window.put(remove, window.get(remove) - 1);
                }
            }
        }

        return  len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
