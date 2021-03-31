package com.leetcode.hard.suggestion;

import java.util.*;

/**
 * 现有一种使用英语字母的火星语言，这门语言的字母顺序与英语顺序不同。
 * 给你一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的
 * 合法字母顺序，返回其中 任意一种 顺序即可。
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 *
 * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 *  
 *
 * 示例 1：
 *
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 * 示例 2：
 *
 * 输入：words = ["z","x"]
 * 输出："zx"
 * 示例 3：
 *
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 *
 * 思路：
 *
 * 将词典中字符串的字符两两对比，只有第一个不同的字符才是正确的排序，如ert和wrf，只能推断出e的优先级高于w，剩余字符的优先级不能推断。
 *
 * 将字符串的优先级构建为图，然后进行拓扑排序。如果图中无环，则将拓扑排序输出，否则顺序是非法的。
 *
 * 注意对于输入"za","zb","ca","cb"，字符关系为a->b、z->c，输出可以为azbc、zacb，只要输出一种即可。
 *
 */
public class LeetCode269 {
    class Solution {
        public String alienOrder(String[] words) {
            //1.构建图
            Map<Character, Set<Character>> map = new HashMap<>();
            for (int i = 0; i < words.length - 1; i++) {
                for (int j = 0; j < words[i].length() && j < words[i + 1].length(); j++) {
                    //如果字符相同，比较下一个
                    if (words[i].charAt(j) == words[i + 1].charAt(j)) continue;
                    //保存第一个不同的字符顺序
                    Set<Character> set = map.getOrDefault(words[i].charAt(j), new HashSet<>());
                    set.add(words[i + 1].charAt(j));
                    map.put(words[i].charAt(j), set);
                    break;
                }
            }

            //2.拓扑排序
            //创建保存入度的数组
            int[] degrees = new int[26];
            Arrays.fill(degrees, -1);
            //注意，不是26字母都在words中出现，所以出度分为两种情况：没有出现的字母出度为-1，出现了的字母的出度为非负数
            for (String str : words) {
                //将出现过的字符的出度设定为0
                for (char c : str.toCharArray())
                    degrees[c - 'a'] = 0;
            }
            for (char key : map.keySet()) {
                for (char val : map.get(key)) {
                    degrees[val - 'a']++;
                }
            }
            //创建StringBuilder保存拓扑排序,拓扑排序的关键是在邻接表的基础上为每一个增加节点增加一个入度属性，
            StringBuilder sb = new StringBuilder();
            //创建一个Queue保存入度为0的节点
            Queue<Character> list = new LinkedList<>();

            int count = 0;//计算图中节点数
            for (int i = 0; i < 26; i++) {
                if (degrees[i] != -1) count++;
                if (degrees[i] == 0) {
                    list.add((char) ('a' + i));
                }
            }

            while (!list.isEmpty()) {
                Character cur = list.poll();
                sb.append(cur);
                //将邻接点出度-1
                if (map.containsKey(cur)) {
                    Set<Character> set = map.get(cur);
                    for (Character c : set) {
                        degrees[c - 'a']--;
                        if (degrees[c - 'a'] == 0) list.add(c);
                    }
                }
            }

            //判断是否有环
            if (sb.length() != count) return "";
            else return sb.toString();

        }
    }
}
