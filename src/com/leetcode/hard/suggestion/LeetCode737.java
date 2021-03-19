package com.leetcode.hard.suggestion;

import com.leetcode.UF;

import java.util.*;

/**
 * 给定两个句子 words1, words2 （每个用字符串数组表示），和一个相似单词对的列表 pairs ，判断是否两个句子是相似的。
 * 例如，当相似单词对是 pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]]的时候，
 * words1 = ["great", "acting", "skills"] 和 words2 = ["fine", "drama", "talent"] 是相似的。注意相似关系是 具有 传递性的。
 * 例如，如果 "great" 和 "fine" 是相似的，"fine" 和 "good" 是相似的，则 "great" 和 "good" 是相似的。
 * 而且，相似关系是具有对称性的。例如，"great" 和 "fine" 是相似的相当于 "fine" 和 "great" 是相似的。
 * 并且，一个单词总是与其自身相似。例如，句子 words1 = ["great"], words2 = ["great"], pairs = [] 是相似的，
 * 尽管没有输入特定的相似单词对。最后，句子只会在具有相同单词个数的前提下才会相似。所以一个句子 words1 = ["great"]
 * 永远不可能和句子 words2 = ["doubleplus","good"] 相似。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sentence-similarity-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode737 {
    public boolean areSentencesSimilarTwo(
            String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, List<String>> graph = new HashMap();
        for (String[] pair: pairs) {
            for (String p: pair) if (!graph.containsKey(p)) {
                graph.put(p, new ArrayList());
            }
            graph.get(pair[0]).add(pair[1]);
            graph.get(pair[1]).add(pair[0]);
        }

        for (int i = 0; i < words1.length; ++i) {
            String w1 = words1[i], w2 = words2[i];
            Stack<String> stack = new Stack();
            Set<String> seen = new HashSet();
            stack.push(w1);
            seen.add(w1);
            search: {
                while (!stack.isEmpty()) {
                    String word = stack.pop();
                    if (word.equals(w2)) break search;
                    if (graph.containsKey(word)) {
                        for (String nei: graph.get(word)) {
                            if (!seen.contains(nei)) {
                                stack.push(nei);
                                seen.add(nei);
                            }
                        }
                    }
                }
                return false;
            }
        }
        return true;
    }

    public boolean areSentencesSimilarTwo_UF(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        Map<String, Integer> wordToIndex = new HashMap<>();
        int count = 0;
        UF uf = new UF(2 * pairs.length);
        for (String[] pair : pairs) {
            for (String p : pair) {
                if (!wordToIndex.containsKey(p)) {
                    wordToIndex.put(p, count++);
                }
            }
            uf.unite(wordToIndex.get(pair[0]), wordToIndex.get(pair[1]));
        }

        for (int i = 0; i < words1.length; i++) {
            if (!wordToIndex.containsKey(words1[i]) || !wordToIndex.containsKey(words2[i])
                    || uf.isConnected(wordToIndex.get(words1[i]), wordToIndex.get(words2[i]))) {
                return false;
            }
        }
        return true;
    }
}
