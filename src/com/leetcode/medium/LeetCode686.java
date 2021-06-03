package com.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 *
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-string-match
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode686 {
    public int repeatedStringMatch(String a, String b) {
        Set<Character> aSet = getChars(a);
        Set<Character> bSet = getChars(b);
        if (!aSet.containsAll(bSet)) {
            return -1;
        }
        int ans = 0;
        final int cap = 10001;
        StringBuilder builder = new StringBuilder(cap);
        while (builder.length() < b.length()) {
            builder.append(a);
            ++ans;
        }
        while (builder.length() < cap && !builder.toString().contains(b)) {
            builder.append(a);
            ++ans;
        }

        return builder.length() < cap ? ans : -1;
    }

    Set<Character> getChars(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }
}
