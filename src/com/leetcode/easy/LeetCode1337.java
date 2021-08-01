package com.leetcode.easy;

import java.util.Arrays;
import java.util.Comparator;

public class LeetCode1337 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int row = mat.length;
        int[] freq = new int[row];
        for (int i = 0; i < row; i++) {
            freq[i] = Arrays.stream(mat[i]).sum();
        }
        Integer[] index = new Integer[row];
        for (int i = 0; i < row; i++) {
            index[i] = i;
        }
        Arrays.sort(index, Comparator.comparingInt(o -> freq[o]));
        return Arrays.stream(index).limit(k).mapToInt(Integer::intValue).toArray();
    }
}