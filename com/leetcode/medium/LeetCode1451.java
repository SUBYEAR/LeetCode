package com.leetcode.medium;

import java.util.Arrays;
import java.util.Comparator;

/*
「句子」是一个用空格分隔单词的字符串。给你一个满足下述格式的句子 text :

句子的首字母大写
text 中的每个单词都用单个空格分隔。
请你重新排列 text 中的单词，使所有单词按其长度的升序排列。如果两个单词的长度相同，则保留其在原句子中的相对顺序。

请同样按上述格式返回新的句子。

 */
public class LeetCode1451 {
    public String arrangeWords(String text) {
        text = text.toLowerCase();
        String[] words = text.split("\\s");
        Arrays.sort(words, Comparator.comparingInt(String::length));
        StringBuilder s = new StringBuilder();
        words[0] =Character.toUpperCase(words[0].charAt(0)) +  words[0].substring(1);
        s.append(words[0]);
        for (int i = 1; i < words.length; i++) {
            s.append(" ").append(words[i]);
        }
        return s.toString();
    }
//    String tempans = Arrays.stream(text.toLowerCase().split(" "))
//            .sorted(Comparator.comparingInt(String::length))
//            .collect(Collectors.joining(" ", "", ""));
//
//    return tempans.substring(0, 1).toUpperCase() + tempans.substring(1);
}
