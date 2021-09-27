package com.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 你将会得到一份单词表 words，一个字母表 letters （可能会有重复字母），以及每个字母对应的得分情况表 score。
 *
 * 请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由 letters 里的字母拼写出的 任意 属于 words 单词子集中，分数最高的单词集合的得分。
 *
 * 单词拼写游戏的规则概述如下：
 *
 * 玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词。
 * 可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。
 * 单词表 words 中每个单词只能计分（使用）一次。
 * 根据字母得分情况表score，字母 'a', 'b', 'c', ... , 'z' 对应的得分分别为 score[0], score[1], ..., score[25]。
 * 本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。
 * 提示：
 *
 * 1 <= words.length <= 14
 * 1 <= words[i].length <= 15
 * 1 <= letters.length <= 100
 * letters[i].length == 1
 * score.length == 26
 * 0 <= score[i] <= 10
 * words[i] 和 letters[i] 只包含小写的英文字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-score-words-formed-by-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1255 {
    int res = 0;
    int[] score;
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        this.score = score;
        int[] lettersFreq = getFreq(letters);
        List<String> list = preprocess(words, lettersFreq);
        StringBuilder path = new StringBuilder();
        backtrack(list, 0, lettersFreq, path);
        return res;
    }

    List<String> preprocess(String[] words, int[] lettersFreq) {
        List<String> list = new ArrayList<>();
        for (String word : words) {
            int[] freq = getFreq(word.toCharArray());
            if (isContain(lettersFreq, freq)) {
                list.add(word);
            }
        }
        return list;
    }

    void backtrack(List<String> word, int start, int[] lettersFreq, StringBuilder path) {
        int score = getScore(path.toString(), this.score);
        // System.out.println(path);
        if (res < score) {
            res = score;
        }
        for (int i = start; i <word.size(); i++) {
            String s = word.get(i);
            int[] freq = getFreq(s.toCharArray());
            if (!isContain(lettersFreq, freq)) {
                continue;
            }

            path.append(s);
            AddAndDelete(lettersFreq, freq, true);
            backtrack(word, i + 1, lettersFreq, path);
            path.delete(path.length() -s.length(), path.length());
            AddAndDelete(lettersFreq, freq, false);
        }
    }

    int[] getFreq(char[] chars) {
        int[] freq = new int[26];
        for (char ch : chars) {
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

    void AddAndDelete(int[] a, int[] b, boolean isDeleted) {
        int coff = isDeleted ? -1 : 1;
        for (int i = 0; i < a.length; i++) {
            a[i] += (b[i] * coff);
        }
    }

    int getScore(String s, int[] score) {
        int ans = 0;
        for (char ch : s.toCharArray()) {
            ans += score[ch - 'a'];
        }
        return ans;
    }
}
