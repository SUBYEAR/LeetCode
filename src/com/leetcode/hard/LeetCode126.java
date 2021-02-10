package com.leetcode.hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换后得到的单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回一个空列表。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-ladder-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode126 {
    private Map<String, Integer> wordToId = new HashMap<>();
    private ArrayList<String> idToWord = new ArrayList<>();
    private ArrayList<Integer> [] edges;


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        int id = 0;
        for (String word : wordList) {
            if (!wordToId.containsKey(word)) {
                wordToId.put(word, id++);
                idToWord.add(word);
            }
        }

        if (!wordToId.containsKey(endWord)) {
            return new ArrayList<>();
        }

        if (wordToId.containsKey(beginWord)) {
            wordToId.put(beginWord, id++);
            idToWord.add(beginWord);
        }

        edges = new ArrayList[idToWord.size()];
        for (int i = 0; i < idToWord.size(); i++) {
            for (int j = i + 1; j < idToWord.size(); j++) {
                if (isConnected(idToWord.get(i), idToWord.get(j))) {
                    edges[i].add(j);
                    edges[j].add(i);
                }
            }
        }



        return null;
    }

    boolean isConnected(String s1, String s2) {
        int dif = 0;
        for (int i = 0; i < s1.length() && dif < 2; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++dif;
            }
        }
        return dif == 1;
//        for (int i = 0; i < len; i++) {
//            for (char c = 'a'; c <= 'z'; c++) {
//                 if (s1.replace(s1.charAt(i), c).equals(s2)) {
//                     return true;
//                 }
//            }
//        }

//        return false;
    }

    //private static final int INF = 1 << 20;
    //
    //    private Map<String, Integer> wordId; // 单词到id的映射
    //
    //    private ArrayList<String> idWord; // id到单词的映射
    //
    //    private ArrayList<Integer>[] edges; // 图的边
    //
    //    public LeetCode126() {
    //        wordId = new HashMap<>();
    //        idWord = new ArrayList<>();
    //    }
    //
    //    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    //        int id = 0;
    //        // 将wordList所有单词加入wordId中 相同的只保留一个 // 并为每一个单词分配一个id
    //        for (String word : wordList) {
    //            if (!wordId.containsKey(word)) {
    //                wordId.put(word, id++);
    //                idWord.add(word);
    //            }
    //        }
    //        // 若endWord不在wordList中 则无解
    //        if (!wordId.containsKey(endWord)) {
    //            return new ArrayList<>();
    //        }
    //        // 把beginWord也加入wordId中
    //        if (!wordId.containsKey(beginWord)) {
    //            wordId.put(beginWord, id++);
    //            idWord.add(beginWord);
    //        }
    //
    //        // 初始化存边用的数组
    //        edges = new ArrayList[idWord.size()];
    //        for (int i = 0; i < idWord.size(); i++) {
    //            edges[i] = new ArrayList<>();
    //        }
    //        // 添加边
    //        for (int i = 0; i < idWord.size(); i++) {
    //            for (int j = i + 1; j < idWord.size(); j++) {
    //                // 若两者可以通过转换得到 则在它们间建一条无向边
    //                if (transformCheck(idWord.get(i), idWord.get(j))) {
    //                    edges[i].add(j);
    //                    edges[j].add(i);
    //                }
    //            }
    //        }
    //
    //        int dest = wordId.get(endWord); // 目的ID
    //        List<List<String>> res = new ArrayList<>(); // 存答案
    //        int[] cost = new int[id]; // 到每个点的代价
    //        for (int i = 0; i < id; i++) {
    //            cost[i] = INF; // 每个点的代价初始化为无穷大
    //        }
    //
    //        // 将起点加入队列 并将其cost设为0
    //        Queue<ArrayList<Integer>> q = new LinkedList<>();
    //        ArrayList<Integer> tmpBegin = new ArrayList<>();
    //        tmpBegin.add(wordId.get(beginWord));
    //        q.add(tmpBegin);
    //        cost[wordId.get(beginWord)] = 0;
    //
    //        // 开始广度优先搜索
    //        while (!q.isEmpty()) {
    //            ArrayList<Integer> now = q.poll();
    //            int last = now.get(now.size() - 1); // 最近访问的点
    //            if (last == dest) { // 若该点为终点则将其存入答案res中
    //                ArrayList<String> tmp = new ArrayList<>();
    //                for (int index : now) {
    //                    tmp.add(idWord.get(index)); // 转换为对应的word
    //                }
    //                res.add(tmp);
    //            } else { // 该点不为终点 继续搜索
    //                for (int i = 0; i < edges[last].size(); i++) {
    //                    int to = edges[last].get(i);
    //                    // 此处<=目的在于把代价相同的不同路径全部保留下来
    //                    if (cost[last] + 1 <= cost[to]) {
    //                        cost[to] = cost[last] + 1;
    //                        // 把to加入路径中
    //                        ArrayList<Integer> tmp = new ArrayList<>(now);
    //                        tmp.add(to);
    //                        q.add(tmp); // 把这个路径加入队列
    //                    }
    //                }
    //            }
    //        }
    //        return res;
    //    }
    //
    //    // 两个字符串是否可以通过改变一个字母后相等
    //    boolean transformCheck(String str1, String str2) {
    //        int differences = 0;
    //        for (int i = 0; i < str1.length() && differences < 2; i++) {
    //            if (str1.charAt(i) != str2.charAt(i)) {
    //                ++differences;
    //            }
    //        }
    //        return differences == 1;
    //    }
    //
    //    // 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度
    //    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    //        // Since all words are of same length.
    //        int L = beginWord.length();
    //
    //        // Dictionary to hold combination of words that can be formed,
    //        // from any given word. By changing one letter at a time.
    //        Map<String, List<String>> allComboDict = new HashMap<>();
    //
    //        wordList.forEach(
    //                word -> {
    //                    for (int i = 0; i < L; i++) {
    //                        // Key is the generic word
    //                        // Value is a list of words which have the same intermediate generic word.
    //                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
    //                        List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
    //                        transformations.add(word);
    //                        allComboDict.put(newWord, transformations);
    //                    }
    //                });
    //
    //        // Queue for BFS
    //        Queue<Pair<String, Integer>> Q = new LinkedList<>();
    //        Q.add(new Pair(beginWord, 1));
    //
    //        // Visited to make sure we don't repeat processing same word.
    //        Map<String, Boolean> visited = new HashMap<>();
    //        visited.put(beginWord, true);
    //
    //        while (!Q.isEmpty()) {
    //            Pair<String, Integer> node = Q.remove();
    //            String word = node.getKey();
    //            int level = node.getValue();
    //            for (int i = 0; i < L; i++) {
    //
    //                // Intermediate words for current word
    //                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
    //
    //                // Next states are all the words which share the same intermediate state.
    //                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
    //                    // If at any point if we find what we are looking for
    //                    // i.e. the end word - we can return with the answer.
    //                    if (adjacentWord.equals(endWord)) {
    //                        return level + 1;
    //                    }
    //                    // Otherwise, add it to the BFS Queue. Also mark it visited
    //                    if (!visited.containsKey(adjacentWord)) {
    //                        visited.put(adjacentWord, true);
    //                        Q.add(new Pair(adjacentWord, level + 1));
    //                    }
    //                }
    //            }
    //        }
    //
    //        return 0;
    //    }
}
