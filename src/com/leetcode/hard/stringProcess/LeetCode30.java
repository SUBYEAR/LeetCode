package com.leetcode.hard.stringProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个字符串 s 和一些 长度相同 的单词 words 。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode30 {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(words);
        String join = String.join("", words);
        int[] freq = getFreq(join);
        int length = words.length;
        int wordLen = words[0].length();
        int left = 0;
        int right = 0;
        int[] slide = new int[26];
        while (right < s.length()) {
            ++slide[s.charAt(right) - 'a'];
            right++;
            while (right - left >= join.length()) {
                if (Arrays.equals(slide, freq) && join.equals(normalize(s.substring(left, right), wordLen, length))) {
                    res.add(left);
                }
                --slide[s.charAt(left) - 'a'];
                left++;
            }
        }
        return res;
    }

    String normalize(String sub, int wordLen, int length) {
        String[] arr = new String[length];
        for (int i = 0; i < length; i++) {
            arr[i] = sub.substring(i * wordLen, (i + 1) * wordLen);
        }
        Arrays.sort(arr);
        return String.join("", arr);
    }

    int[] getFreq(String s) {
        int[] res = new int[26];
        for (char ch : s.toCharArray()) {
            ++res[ch - 'a'];
        }
        return res;
    }
}
