/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 给定两个由一些 闭区间 组成的列表，每个区间列表都是成对不相交的，并且已经排序。
 * 返回这两个区间列表的交集。
 * （形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b。
 * 两个闭区间的交集是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3]。）
 *
 * @since 2020-06-30
 */
public class LeetCode986 {
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        int i = 0;
        int j = 0;
        List<int[]> lists = new LinkedList<>();
        while (i < A.length && j < B.length) {
            int startA = A[i][0];
            int endA = A[i][1];
            int startB = B[j][0];
            int endB = B[j][1];

            if (endB >= startA && endA >= startB) {
                lists.add(new int[] {Math.max(startA, startB), Math.min(endA, endB)});
            }

            if (endB < endA) {
                j++;
            } else {
                i++;
            }
        }
        int[][] res = new int[lists.size()][2];
        i = 0;
        for (int[] section : lists) {
            res[i][0] = section[0];
            res[i][1] = section[1];
            ++i;
        }
        return res;
    }

    // 合并区间
    public int[][] merge(int[][] intervals) {
        int row = intervals.length;
        int col = intervals[0].length;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        ArrayList<Integer[]> merged = new ArrayList<Integer[]>();
        for (int i = 0; i < row; i++) {
            int endPos = merged.size() - 1;
            if (merged.size() == 0 || intervals[i][0] > merged.get(endPos)[1]) { // start 大于 end 不能合并
                Integer[] ib = IntStream.of(intervals[i]).boxed().collect(Collectors.toList()).toArray(new Integer[0]);
                merged.add(ib);
            } else { // 合并区间
                Integer right = Math.max(intervals[i][1], merged.get(endPos)[1]);
                merged.set(endPos, new Integer[] {merged.get(endPos)[0], right});
            }
        }

        int[][] res = new int[merged.size()][col];
        for (int i = 0; i < merged.size(); i++) {
            res[i] = new int[col];
            res[i][0] = merged.get(i)[0];
            res[i][1] = merged.get(i)[1];
        }
        return res;
    }
}
