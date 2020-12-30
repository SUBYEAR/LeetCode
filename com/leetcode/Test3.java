/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 功能描述
 *
 * @since 2020-07-24
 */
public class Test3 {
    public int minOperations(int[] water, int cnt) {
        List<Integer> sub = new LinkedList<>();
        List<List<Integer>> subArr = getSubArr(water, 0, cnt, sub);
        int res = 1000000007;
        for (List<Integer> l : subArr) {
            int max = 0;
            int cur = 0;
            for (int val : l) {
                max = Math.max(max, val);
            }
            for (int val : l) {
                cur = (cur + (max - val)) % 1000000007;
            }
            res = Math.min(res, cur);
        }
        return res;
    }


    private List<List<Integer>> getSubArr(int[] arr, int begin, int num, List<Integer> sub) {
        List<List<Integer>> list = new LinkedList<>();

        if (num == 0) {
            list.add(new LinkedList<>(sub));
            return list;
        }

        if (begin == arr.length) {
            return list;
        }

        sub.add(arr[begin]);
        list.addAll(getSubArr(arr,begin + 1, num - 1, sub));
        sub.remove(sub.size() - 1);
        list.addAll(getSubArr(arr,begin + 1, num , sub));
        return list;
    }

    public int minOperations_window(int[] water, int cnt) {
        Arrays.sort(water);
        int left = 0;
        int right = 0;
        int res = 1000000007;
        int cur = 0;
        while (right < water.length) {
            right++;
            cur += water[right];
            if (right - left == cnt) {
                Math.min(res, (right - left + 1) * water[right] - cur);
                cur -= water[left];
                left++;
            }
        }
        return res;
    }
    public int divideGroup(int[] tasks, int[][] mutexPairs) {
        if (tasks.length == 0 || mutexPairs.length == 0) {
            return 1;
        }
        int mm = mutexPairs.length;
        Map<Integer, List<Integer>> mutexs = new HashMap<>();
        for (int i = 0; i < mm; i++) {
            int row = mutexPairs[i][0];
            int col = mutexPairs[i][1];
            mutexs.computeIfAbsent(row, unused -> new ArrayList<>()).add(col);
            mutexs.computeIfAbsent(col, unused -> new ArrayList<>()).add(row);
        }

        int count = 0;
        List<Integer> history = new ArrayList<>();
        for (int i = 0; i < tasks.length; i++) {
            int cur = tasks[i];
            history.add(cur);
            List<Integer> muts = mutexs.getOrDefault(cur, new ArrayList<>());
            if (muts.isEmpty()) {
                continue;
            }
            for (int k = 0; k < history.size(); k++) {
                if (muts.contains(history.get(k))) {
                    count = count + 1;
                    history.clear();
                    history.add(cur);
                    break;
                }
            }

        }

        return count + 1;
    }

}
