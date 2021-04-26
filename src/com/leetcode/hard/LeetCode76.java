package com.leetcode.hard;

import java.util.HashMap;
import java.util.Map;
/**
 * 给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 输入：S = "ADOBECODEBANC", T = "ABC"
 * 输出："BANC"
 */
public class LeetCode76 {
    public String minWindow(String s, String t) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = new HashMap<>();
        int valid = 0;
        for (char ch : t.toCharArray()) {
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
