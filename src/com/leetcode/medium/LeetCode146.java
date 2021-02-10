/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *  
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * @since 2020-05-25
 */
public class LeetCode146 {
    private int capacity;

    public Map<Integer, Integer> map;

    public LeetCode146(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<Integer, Integer>() {
            private static final long serialVersionUID = 2840765021401040842L;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int res = map.get(key);
        map.remove(key);
        map.put(key, res);
        return res;
    }

    public void put(int key, int value) {
        map.put(key, value);
    }
}
