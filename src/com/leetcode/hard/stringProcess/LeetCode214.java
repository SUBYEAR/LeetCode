package com.leetcode.hard.stringProcess;

import java.util.Arrays;

/**
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "aacecaaa"
 * 输出："aaacecaaa"
 * 示例 2：
 *
 * 输入：s = "abcd"
 * 输出："dcbabcd"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode214 {
    public String shortestPalindrome(String s) { //使用动态规划的思路预处理字符那些是回文的会超时(使用滚动数组的前提)
        // 使用KMP算法找到最长的前缀回文串,使用s作为模式串,s的逆序作为匹配串
        int n = s.length();
        int[] next = new int[n];
        Arrays.fill(next, -1);
        // 求next数组注意这里的数组长度是n比起常规的next数组长度少了1，那么里面的下标j就有变化了
        for (int i = 1; i < n; i++) {
            int j = next[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = next[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                next[i] = j + 1;
            }
        }

        int best = -1;
        for (int i = n - 1; i >= 0; i--) { // 下标从n-1开始相当于s的逆序
            while (best != -1 && s.charAt(best + 1) != s.charAt(i)) {
                best = next[best];
            }
            if (s.charAt(best + 1) == s.charAt(i)) {
                ++best;
            }
        }

        String add = (best == n - 1) ? "" : s.substring(best + 1);
        StringBuilder ans = new StringBuilder(add).reverse();
        ans.append(s);
        return ans.toString();
    }
}
