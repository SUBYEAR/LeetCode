package com.leetcode.medium.passed.sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 */
public class LeetCode451 {
    public String frequencySort(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        Character[] chars = s.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        for (char c : chars) {
            freq.put(c,freq.getOrDefault(c, 0) + 1);
        }

        Arrays.sort(chars, (o1, o2) -> {
            if (freq.get(o2).equals(freq.get(o1))) {
                return o2 - o1;
            }
            return freq.get(o2) - freq.get(o1);
        });
        return Arrays.stream(chars)
                .map(Object::toString)
                .collect( Collectors.joining() );
    }
}

