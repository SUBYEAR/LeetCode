package com.leetcode.medium;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 *
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 */
public class LeetCode767 {
    public String reorganizeString(String S) {
        if (S.length() < 2) return S;
        int[] counts = new int[26];
        int maxCount = 0;
        for (char c : S.toCharArray()) {
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }

        if (maxCount > (S.length() + 1) / 2) return "";

        PriorityQueue<Character> q = new PriorityQueue<>((t0, t1) -> counts[t1 - 'a'] - counts[t0 - 'a']);
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] >= 0) {
                q.offer(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (q.size() > 1) {
            char letter1 = q.poll();
            char letter2 = q.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a';
            int index2 = letter2 - 'a';
            --counts[index1];
            --counts[index2];

            if (counts[index1] > 0) {
                q.offer(letter1);
            }
            if (counts[index2] > 0) {
                q.offer(letter2);
            }
        }

        if (q.size() > 0) {
            sb.append(q.poll());
        }
        return sb.toString();
    }
}
