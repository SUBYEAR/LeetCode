package com.leetcode.medium;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 */
public class LeetCode633 {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }
}

// 双指针解法
//         long left = 0;
//        long right = (long) Math.sqrt(c);
//        while (left <= right) {
//            long sum = left * left + right * right;
//            if (sum == c) {
//                return true;
//            } else if (sum > c) {
//                right--;
//            } else {
//                left++;
//            }
//        }
//        return false;
//    }
