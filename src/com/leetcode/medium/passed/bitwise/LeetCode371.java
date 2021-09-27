package com.leetcode.medium.passed.bitwise;

/**
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
 */
public class LeetCode371 {
    public int getSum(int a, int b) {
        int xor = a ^ b;
        int and = a & b;
        return (and << 1) + xor;
    }
}
