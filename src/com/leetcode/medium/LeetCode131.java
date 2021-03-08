package com.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。
 *
 * 回文串 是正着读和反着读都一样的字符串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 * 示例 2：
 *
 * 输入：s = "a"
 * 输出：[["a"]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode131 {
    List<List<String>> res = new LinkedList<>();
    public List<List<String>> partition(String s) {
        LinkedList<String> path = new LinkedList<String>();
        backtrack(s, 0, path);
        return res;
    }

    void backtrack(String str, int start, LinkedList<String> track) {
        if (start >= str.length()) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int i = start; i < str.length(); i++) {
            if (isPalindrome(str, start, i)) {
                track.add(str.substring(start, i + 1));
                backtrack(str, i + 1, track);
                track.removeLast();
            }
        }
    }

    boolean isPalindrome(String str, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
        }
        return true;
    }
}
