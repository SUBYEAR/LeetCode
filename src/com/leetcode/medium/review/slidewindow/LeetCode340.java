package com.leetcode.medium.review.slidewindow;

import java.util.Collections;
import java.util.HashMap;

/**
 * 给定一个字符串 s ，找出 至多 包含 k 个不同字符的最长子串 T。
 *
 * 示例 1:
 *
 * 输入: s = "eceba", k = 2
 * 输出: 3
 * 解释: 则 T 为 "ece"，所以长度为 3。
 * 示例 2:
 *
 * 输入: s = "aa", k = 1
 * 输出: 2
 * 解释: 则 T 为 "aa"，所以长度为 2。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode340 {
        public int lengthOfLongestSubstringKDistinct(String s, int k) {
            int n = s.length();
            if (n*k == 0) return 0;

            // sliding window left and right pointers
            int left = 0;
            int right = 0;
            // hashmap character -> its rightmost position
            // in the sliding window
            HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();

            int max_len = 1;

            while (right < n) {
                // add new character and move right pointer
                hashmap.put(s.charAt(right), right++);

                // slidewindow contains 3 characters
                if (hashmap.size() == k + 1) {
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

/**
 * 算法
 *
 * 使用有序字典代替标准哈希表解决方法 1：
 *
 * 如果字符串为空或者 k 是零的话返回 0。
 * 设置指针为字符串开头 left = 0 和 right = 0，初始化最大子串长度 max_len = 1。
 * 当 right 指针小于 N 时：
 * 如果当前字符 s[right] 已经在有序字典中 hashmap 中，删除它，以保证 hashmap 的第一个元素是滑动窗口的最左侧元素。
 * 将 s[right] 加入有序字典，并右移 right 指针。
 * 如果有序字典 hashmap 包含 k + 1 个不同字符，在哈希表中移去最左出现的字符，移动 left 指针使得滑动窗口只包含 k 个不同字符。
 * 更新 max_len。
 */
// class Solution {
//  public int lengthOfLongestSubstringKDistinct(String s, int k) {
//    int n = s.length();
//    if (n*k == 0) return 0;
//
//    // sliding window left and right pointers
//    int left = 0;
//    int right = 0;
//    // hashmap character -> its rightmost position
//    // in the sliding window
//    LinkedHashMap<Character, Integer> hashmap = new LinkedHashMap<Character, Integer>(k + 1);
//
//    int max_len = 1;
//
//    while (right < n) {
//      Character character = s.charAt(right);
//      // if character is already in the hashmap -
//      // delete it, so that after insert it becomes
//      // the rightmost element in the hashmap
//      if (hashmap.containsKey(character))
//        hashmap.remove(character);
//      hashmap.put(character, right++);
//
//      // slidewindow contains k + 1 characters
//      if (hashmap.size() == k + 1) {
//        // delete the leftmost character
//        Map.Entry<Character, Integer> leftmost = hashmap.entrySet().iterator().next();
//        hashmap.remove(leftmost.getKey());
//        // move left pointer of the slidewindow
//        left = leftmost.getValue() + 1;
//      }
//
//      max_len = Math.max(max_len, right - left);
//    }
//    return max_len;
//  }
//}
//
//作者：LeetCode
//链接：https://leetcode-cn.com/problems/longest-substring-with-at-most-k-distinct-characters/solution/zhi-shao-bao-han-k-ge-bu-tong-zi-fu-de-zui-chang-z/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。