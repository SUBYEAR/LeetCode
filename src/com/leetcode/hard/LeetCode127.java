package com.leetcode.hard;

import javafx.util.Pair;

import java.util.*;
/*
给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

每次转换只能改变一个字母。
转换过程中的中间单词必须是字典中的单词。
说明:

如果不存在这样的转换序列，返回 0。
所有单词具有相同的长度。
所有单词只由小写字母组成。
字典中不存在重复的单词。
你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
示例 1:

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/word-ladder
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        Map<String, List<String>> dict = new HashMap<>();

        wordList.forEach(word -> { // 构建边的过程有一个虚拟节点的意思
            for (int i = 0; i < len; i++) {
                String temp = word.substring(0, i) + "*" + word.substring(i + 1);
                List<String> list = dict.getOrDefault(temp, new ArrayList<>());
                list.add(word);
                dict.put(temp, list);
            }
        });

        Queue<Pair<String, Integer>> queue = new LinkedList<>(); // 节点带了长度信息
        queue.add(new Pair(beginWord, 1));
        Map<String, Boolean> visit = new HashMap<>();
        visit.put(beginWord, true);
        while (!queue.isEmpty()) {
            Pair<String, Integer> cur = queue.remove();
            int level = cur.getValue();
            String key = cur.getKey();

            for (int i = 0; i < len; i++) {
                String newWord = key.substring(0, i) + "*" + key.substring(i + 1);

                for (String adjacent : dict.getOrDefault(newWord, new ArrayList<>())) {
                    if (adjacent.equals(endWord)) {
                        return level + 1;
                    }

                    if (!visit.getOrDefault(adjacent,false)) {
                        visit.put(adjacent, true);
                        queue.add(new Pair(adjacent, level + 1));

                    }
                }
            }

        }

        return 0;
    }
}
