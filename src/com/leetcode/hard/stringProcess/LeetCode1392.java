package com.leetcode.hard.stringProcess;

/**
 * 「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。
 *
 * 给你一个字符串 s，请你返回它的 最长快乐前缀。
 *
 * 如果不存在满足题意的前缀，则返回一个空字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-happy-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1392 {
    public String longestPrefix(String s) {
        // 其实就是kmp算法中求解next数组的过程
        int n = s.length();
        int[] next = new int[n + 1]; // next[i]表示0...i-1字符串前缀和后缀的最长长度
        next[0] = -1;
        int k = 0;
        for (int j = 1; j <= n; j++) {
            k = next[j - 1];
            if (k == -1) {
                next[j] = 0;
            } else {
                while (k != -1 && s.charAt(j - 1) != s.charAt(k)) {
                    k = next[k];
                }
                if (k == -1) {
                    next[j] = 0;
                } else {
                    next[j] = k + 1;
                }
            }
        }
        return next[n] > 0 ? s.substring(0, next[n]) : "";
    }
}
