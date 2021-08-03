package com.leetcode.medium;

import java.util.*;
import java.util.stream.Collectors;

public class LeetCode60 {
    static final char[] arr = new char[] {'1', '2','3','4','5','6','7','8','9'};
    int cnt;
    String res = "";
    public String getPermutation(int n, int k) {

        char[] chars = Arrays.copyOfRange(arr, 0, n);
        backtrack(chars, 0, k);
        return res;
    }

    boolean backtrack(char[] arr, int start, int k) {
        if (start == arr.length) {
            ++cnt;
            if (cnt == k) {
                res = String.valueOf(arr);
                return true;
            }
            return false;
        }

        for (int i = start; i <arr.length; i++) {
            process(arr, start, i);
            if (backtrack(arr, start + 1, k)) {
                return true;
            }

            unprocess(arr, start, i);
        }
        return false;
    }

    private void process(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        char tmp = arr[j];
        for (int k = j; k > i; k--) {
            arr[k] = arr[k - 1];
        }
        arr[i] = tmp;
    }

    private void unprocess(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        char tmp = arr[i];
        for (int k = i; k < j; k++) {
            arr[k] = arr[k + 1];
        }
        arr[j] = tmp;
    }
}
