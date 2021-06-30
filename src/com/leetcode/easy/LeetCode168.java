package com.leetcode.easy;

/**
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 */
public class LeetCode168 {
    public String convertToTitle(int columnNumber) {
        StringBuilder s = new StringBuilder();
        while (columnNumber != 0) {
            --columnNumber;
            int rest = columnNumber % 26;
            char c = (char) ('A' + rest);
            s.append(c);
            columnNumber /= 26;
        }
        return s.reverse().toString();
    }
}
