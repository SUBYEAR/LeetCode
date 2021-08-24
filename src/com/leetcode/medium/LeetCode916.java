package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们给出两个单词数组 A 和 B。每个单词都是一串小写字母。
 *
 * 现在，如果 b 中的每个字母都出现在 a 中，包括重复出现的字母，那么称单词 b 是单词 a 的子集。 例如，“wrr” 是 “warrior” 的子集，但不是 “world” 的子集。
 *
 * 如果对 B 中的每一个单词 b，b 都是 a 的子集，那么我们称 A 中的单词 a 是通用的。
 *
 * 你可以按任意顺序以列表形式返回 A 中所有的通用单词。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode916 {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        // 求words2的并集
        int[] freq = new int[26];
        for (String word : words2) {
            unite(freq, getFreq(word));
        }

        List<String> res = new ArrayList<>();
        for (String word : words1) {
            if (isContain(getFreq(word), freq)) {
                res.add(word);
            }
        }
        return res;
    }

    int[] getFreq(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            ++freq[ch - 'a'];
        }
        return freq;
    }

    boolean isContain(int[] a, int[] b) { // a是否包含b
        for (int i = 0; i < a.length; i++) {
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }

    void unite(int[] a, int[] b) { // 取并集
        for (int i = 0; i < a.length; i++) {
            if (b[i] > a[i]) {
                a[i] = b[i];
            }
        }
    }
}
