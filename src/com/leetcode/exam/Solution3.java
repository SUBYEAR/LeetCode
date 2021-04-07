/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.exam;

import java.util.*;

/**
 * 功能描述
 *
 * @since 2020-12-04
 */
public class Solution3 { // 贪心算法 先处理最后结束的
    public int divideGroup(int[] tasks, int[][] mutexPairs) {
        int[][] index = new int[mutexPairs.length][2];
        for (int j = 0; j < mutexPairs.length; j++) {
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == mutexPairs[j][0]) {
                    index[j][0] = i;
                }
                if (tasks[i] == mutexPairs[j][1]) {
                    index[j][1] = i;
                }
            }
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        for (int[] arr : index) {
            int max = Math.max(arr[0], arr[1]);
            arr[0] = arr[0] == max ? arr[1] : arr[0];
            arr[1] = max;
            queue.add(arr);
        }

        int res = 0;
        int start = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            start = cur[0];

            while (!queue.isEmpty() && start < queue.peek()[1]) {
                start = queue.poll()[0];
            }
            ++res;
        }

        return res + 1;
    }

    public int divideGroup2(int[] tasks, int[][] mutexPairs) {
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
