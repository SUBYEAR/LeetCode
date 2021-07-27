package com.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 */
public class Interview1002 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<List<Integer>, List<String>> map = new HashMap<>();
        for (String s : strs) {
            List<Integer> freq = getFreq(s);
            List<String> syn = map.getOrDefault(freq, new ArrayList<>());
            syn.add(s);
            map.put(freq, syn);
        }
        List<List<String>> res = new ArrayList<>(map.values());
        return res;
    }

    public List<Integer> getFreq(String s) {
        int len = s.length();
        int[] freq = new int[26];

        for (int i = 0; i < len; i++) {
            ++freq[s.charAt(i) - 'a'];

        }

        return Arrays.stream(freq).boxed().collect(Collectors.toList());
    }
}
