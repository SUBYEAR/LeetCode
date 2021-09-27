package com.leetcode.hard.backtrack;

import java.util.Arrays;

/**
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
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
