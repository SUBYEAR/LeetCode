package com.leetcode.medium;



import java.util.*;

/**
 *
 */
public class LeetCode981 {
    TreeMap<Integer,Map<String, String>> list;
    public LeetCode981() {
       list = new TreeMap<>();
    }


    public void set(String key, String value, int timestamp) {
        Map<String, String> map = list.getOrDefault(timestamp, new HashMap<>());
        map.put(key, value);
        list.put(timestamp, map);
    }

    public String get(String key, int timestamp) {

    }

}
