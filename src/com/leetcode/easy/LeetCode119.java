package com.leetcode.easy;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class LeetCode119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> pre = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    cur.add(1);
                    continue;
                }

                cur.add(pre.get(j - 1) + pre.get(j));
            }
            pre = cur;
        }
        return pre;
    }
}
//        List<List<Integer>> C = new ArrayList<>();
//        for (int i = 0; i <= rowIndex; i++) {
//            List<Integer> row = new ArrayList<>();
//            for (int j = 0; j <= i; j++) {
//                if (j == 0 || j == i) {
//                    row.add(1);
//                    continue;
//                }
//
//                row.add(C.get(i - 1).get(j - 1) + C.get(i - 1).get(j));
//            }
//            C.add(row);
//        }
//        return C.get(rowIndex);
