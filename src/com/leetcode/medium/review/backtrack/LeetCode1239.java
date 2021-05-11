package com.leetcode.medium.review.backtrack;

import java.util.List;

/**
 * 给定一个字符串数组 arr，字符串 s 是将 arr 某一子序列字符串连接所得的字符串，如果 s 中的每一个字符都只出现过一次，
 * 那么它就是一个可行解。
 * 请返回所有可行解 s 中最长长度。
 * 示例 1：
 *
 * 输入：arr = ["un","iq","ue"]
 * 输出：4
 * 解释：所有可能的串联组合是 "","un","iq","ue","uniq" 和 "ique"，最大长度为 4。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1239 {
    int res = 0;
    public int maxLength(List<String> arr) {
        backtrack(arr, 0, new StringBuilder());
        return res;
    }

    void backtrack(List<String> arr, int start, StringBuilder path) {
        res = Math.max(res, path.length());
        for (int i = start; i < arr.size(); i++) {
            String s = arr.get(i);
            if (!isValid(s+ path)) {
                continue;
            }
            path.append(s);
            backtrack(arr, i + 1, path);
            path.delete(path.length() - s.length(), path.length());
        }
    }

    boolean isValid(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            if (freq[c - 'a'] > 0) {
                return false;
            }
            ++freq[c - 'a'];
        }
        return true;
    }
}
