package com.leetcode.easy;

public class LeetCode191 {
    public int hammingWeight(int n) {
        int ct = Integer.bitCount(n);
        System.out.println("System call: " + ct);
        int num = 0;
        while (n != 0) {
            n = n & (n - 1);
            num++;
        }
        return num;
    }
}
