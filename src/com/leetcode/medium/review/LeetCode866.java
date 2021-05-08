/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 求出大于或等于 N 的最小回文素数。
 * 回顾一下，如果一个数大于 1，且其因数只有 1 和它自身，那么这个数是素数。
 * 例如，2，3，5，7，11 以及 13 是素数。
 * 回顾一下，如果一个数从左往右读与从右往左读是一样的，那么这个数是回文数。
 * 例如，12321 是回文数。
 *
 * @since 2020-05-27
 */
public class LeetCode866 {
    // 知识点： “偶数长度的回文数”中只有11是素数，其他的都可以被11整除。
    public int primePalindrome(int N) {
        for (int L = 1; L <= 5; ++L) {
            // Check for odd-length palindromes
            // 回文根的列举是1~9, 10~99, 100~999
            for (int root = (int) Math.pow(10, L - 1); root < (int) Math.pow(10, L); ++root) { // 如果 123 是 12321 的回文根，那么下一个回文串 12421 的回文根就是 124
                StringBuilder sb = new StringBuilder(Integer.toString(root));
                for (int k = L - 2; k >= 0; --k) {
                    sb.append(sb.charAt(k));
                }
                int x = Integer.valueOf(sb.toString());
                if (x >= N && isPrime(x)) {
                    return x;
                }
                // If we didn't check for even-length palindromes:
                // return N <= 11 ? min(x, 11) : x
            }
            // 一个回文根可能对应两个回文串，例如 123321123321，1232112321 的回文根就都是 123123
            // Check for even-length palindromes
            for (int root = (int) Math.pow(10, L - 1); root < (int) Math.pow(10, L); ++root) {
                StringBuilder sb = new StringBuilder(Integer.toString(root));
                for (int k = L - 1; k >= 0; --k) {
                    sb.append(sb.charAt(k));
                }
                int x = Integer.valueOf(sb.toString());
                if (x >= N && isPrime(x)) {
                    return x;
                }
            }
        }

        throw null;
    }

    List<Integer> getPrime(int n) {
        List<Integer> res = new LinkedList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    List<Integer> getPrimes(int n) {
        List<Integer> res = new LinkedList<>();
        ArrayList<Boolean> flag = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            flag.add(Boolean.TRUE);
        }

        for (int i = 2; i < Math.sqrt(n); i++) {
            for (int j = 2; i * j <= n; j++) {
                flag.set(i * j, Boolean.FALSE);
            }
        }

        for (int i = 2; i < n; i++) {
            if (flag.get(i) == Boolean.TRUE) {
                res.add(i);
            }
        }
        return res;
    }

    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        for (int i = 2; i * i < n; i++) {
            if (isPrim[i]) {
                for (int j = i * i; j < n; j += i) {
                    isPrim[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrim[i]) {
                count++;
            }
        }

        return count;
    }

    boolean isPalindrome(int n) { // 或者利用回文数逆序后的值来判断
        String s = Integer.toString(n);
        int end = s.length() - 1;
        for (int i = 0; i <= end; i++, end--) {
            if (s.charAt(i) != s.charAt(end)) {
                return false;
            }
        }
        return true;
    }
}
