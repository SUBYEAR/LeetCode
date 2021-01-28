package com.leetcode;

import java.util.LinkedList;
import java.util.List;

/*
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 */
public class LeetCode557 {
    public String reverseWords(String s) {
        int length = s.length();
        char[] chars = s.toCharArray();
        StringBuilder res = new StringBuilder();
        List<String> temp = new LinkedList<>();

        for (int i = 0; i < length; i++)  {
            if (chars[i] == ' ') {
                temp.add(res.toString());
                res.delete(0, res.length());
                continue;
            }
            res.insert(0, chars[i]);
        }
        temp.add(res.toString());
        return String.join(" ", temp);
    }
}
