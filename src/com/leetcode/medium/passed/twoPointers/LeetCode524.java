package com.leetcode.medium.passed.twoPointers;

import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary 作为字典，找出并返回字典中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 *
 * 如果答案不止一个，返回长度最长且字典序最小的字符串。如果答案不存在，则返回空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode524 {
    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((o1, o2) -> {
            if (o2.length() == o1.length()) {
                return o1.compareTo(o2);
            }
            return o2.length() - o1.length();
        });

        for (String str : dictionary) {
            if (isSubstring(str, s)) {
                return str;
            }
        }
        return  "";
    }

    private boolean isSubstring(String s, String other) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < other.length()) {
            if (s.charAt(i) == other.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == s.length();
    }
}
