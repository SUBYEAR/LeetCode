/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.easy;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * 字符 数值
 * I 1
 * V 5
 * X 10
 * L 50
 * C 100
 * D 500
 * M 1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9
 * 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 *
 * @since 2020-05-07
 */
public class LeetCode13 {
    public int romanToInt(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == 'I') {
                if ((i + 1 < s.length()) && s.charAt(i + 1) == 'V') {
                    res += 4;
                    ++i;
                } else if ((i + 1 < s.length()) && s.charAt(i + 1) == 'X') {
                    res += 9;
                    ++i;
                } else {
                    res += 1;
                }
            } else if (s.charAt(i) == 'V') {
                res += 5;
            } else if (s.charAt(i) == 'X') {
                if ((i + 1 < s.length()) && s.charAt(i + 1) == 'L') {
                    res += 40;
                    ++i;
                } else if ((i + 1 < s.length()) && s.charAt(i + 1) == 'C') {
                    res += 90;
                    ++i;
                } else {
                    res += 10;
                }
            } else if (s.charAt(i) == 'L') {
                res += 50;
            } else if (s.charAt(i) == 'C') {
                if ((i + 1 < s.length()) && s.charAt(i + 1) == 'D') {
                    res += 400;
                    ++i;
                } else if ((i + 1 < s.length()) && s.charAt(i + 1) == 'M') {
                    res += 900;
                    ++i;
                } else {
                    res += 100;
                }
            } else if (s.charAt(i) == 'D') {
                res += 500;
            } else {
                res += 1000;
            }
        }
        return res;
    }

    public int romanToInt2(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int num = getValue(s.charAt(i));
            if (preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch (ch) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
