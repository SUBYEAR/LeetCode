package com.leetcode.medium;

import java.util.*;

/**
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 */
public class LeetCode692 {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> wordToId = new HashMap<>();
        ArrayList<String> idToWord = new ArrayList<>();
        ArrayList<Integer> cnt = new ArrayList<>();
        int index = 0;
        for (String word : words) {
            if (wordToId.containsKey(word)) {
                int id = wordToId.get(word);
                cnt.set(id, cnt.get(id) + 1);
            } else {
                wordToId.put(word, index);
                index++;
                idToWord.add(word);
                cnt.add(1);
            }
        }
        Integer[] indices = new Integer[cnt.size()];
        for (int i = 0; i < indices.length; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, (o1,o2) -> {
            if (cnt.get(o2) == cnt.get(o1)) {
                return idToWord.get(o1).compareTo(idToWord.get(o2));
            } else {
                return cnt.get(o2).compareTo(cnt.get(o1));
            }
        });
        List<String> res = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            res.add(idToWord.get(indices[i]));
        }
        return res;
    }
}
