package com.leetcode.medium.review;

import java.util.Collections;
import java.util.HashMap;

/**
 * 给定一个字符串 s ，找出 至多 包含两个不同字符的最长子串 t ，并返回该子串的长度。
 *
 * 示例 1:
 *
 * 输入: "eceba"
 * 输出: 3
 * 解释: t 是 "ece"，长度为3。
 * 示例 2:
 *
 * 输入: "ccaabbb"
 * 输出: 5
 * 解释: t 是 "aabbb"，长度为5。
 *
 算法

 现在我们可以写出如下算法：

 如果 N 的长度小于 3 ，返回 N 。
 将左右指针都初始化成字符串的左端点 left = 0 和 right = 0 ，且初始化最大子字符串为 max_len = 2
 当右指针小于 N 时：
 如果 hashmap 包含小于 3 个不同字符，那么将当前字符 s[right] 放到 hashmap 中并将右指针往右移动一次。
 如果 hashmap 包含 3 个不同字符，将最左边的字符从 哈希表中删去，并移动左指针，以便滑动窗口只包含 2 个不同的字符。
 更新 max_len
 */
public class LeetCode159 {
        public int lengthOfLongestSubstringTwoDistinct(String s) {
            int n = s.length();
            if (n < 3) return n;

            // sliding window left and right pointers
            int left = 0;
            int right = 0;
            // hashmap character -> its rightmost position
            // in the sliding window
            HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

            int max_len = 2;

            while (right < n) {
                // slidewindow contains less than 3 characters
                if (hashmap.size() < 3) {
                    hashmap.put(s.charAt(right), right++);
                }

                // slidewindow contains 3 characters
                if (hashmap.size() == 3) {
                    // delete the leftmost character
                    int del_idx = Collections.min(hashmap.values());
                    hashmap.remove(s.charAt(del_idx));
                    // move left pointer of the slidewindow
                    left = del_idx + 1;
                }

                max_len = Math.max(max_len, right - left);
            }
            return max_len;
        }
}
