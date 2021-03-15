/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.easy;

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

// class Solution {
//    public boolean isValid(String s) {
//        int n = s.length();
//        if (n % 2 == 1) {
//            return false;
//        }
//
//        Map<Character, Character> pairs = new HashMap<Character, Character>() {{
//            put(')', '(');
//            put(']', '[');
//            put('}', '{');
//        }};
//        Deque<Character> stack = new LinkedList<Character>();
//        for (int i = 0; i < n; i++) {
//            char ch = s.charAt(i);
//            if (pairs.containsKey(ch)) {
//                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) {
//                    return false;
//                }
//                stack.pop();
//            } else {
//                stack.push(ch);
//            }
//        }
//        return stack.isEmpty();
//    }
//}
