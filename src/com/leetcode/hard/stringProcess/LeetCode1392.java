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
        int n = s.length();
        int[] next = getNextArr(s); // next[i]表示0...i-1字符串前缀和后缀的最长长度
        return next[n] > 0 ? s.substring(0, next[n]) : "";
    }

    // 最大长度表求出了 next 数组后，从而有失配时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next值
    int[] getNextArr(String s) { // 标准的next的数组都是长度比字符串多1且next[0]值为-1
        // next 数组相当于“最大长度值” 整体向右移动一位，然后初始值赋为 -1
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
        return next;
    }

    int KmpSearch(String s, String p) {
        int i = 0;
        int j = 0;
        int sLen = s.length();
        int pLen = p.length();
        int[] next = getNextArr(p);

        while (i < sLen && j < pLen) {
            // 如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {
                // 如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                // next[j]即为j所对应的next值
                j = next[j];
            }
        }

        return j == pLen ? i - j : -1;
    }
}
