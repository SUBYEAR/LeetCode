/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 功能描述
 *
 * @since 2020-08-14
 */
public class LeetCode20 {
    static Map<Character, Character> match = new HashMap<>();
    {
        match.put('(', ')');
        match.put('[', ']');
        match.put('{', '}');
        match.put('X', 'X');
    }
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                st.push(c);
            } else {
                Character top = st.isEmpty() ? 'X' : st.pop();
                if (match.get(top) != c) {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
}
