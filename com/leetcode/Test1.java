/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 功能描述
 *
 * @since 2020-07-24
 */
public class Test1 {
    public int[] relativeSeqSort(int[] arr1, int[] arr2) {
        List<Integer> arr2List = new LinkedList<>();
        for (int val : arr2) {
            arr2List.add(val);
        }
        List<Integer> diff = new LinkedList<>();
        Map<Integer, ArrayList<Integer>> info = new HashMap<>();
        for (int i = 0; i < arr1.length; i++) {
            if (!arr2List.contains(arr1[i])) {
                diff.add(arr1[i]);
            } else {
                ArrayList<Integer> position = info.getOrDefault(arr1[i], new ArrayList<>());
                position.add(i);
                info.put(arr1[i], position);
            }
        }
        Collections.sort(diff);
        int index = 0;
        for (int val : arr2) {
            int j = 0;
            ArrayList<Integer> position = info.get(val);
            while (j++ < position.size()) {
                arr1[index++] = val;
            }
        }
        while (!diff.isEmpty()) {
            arr1[index++] = diff.get(0);
            diff.remove(0);
        }
        return arr1;
    }
}
