/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 *
 * @since 2020-05-28
 */
public class LeetCode166 {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        if (numerator < 0 ^ denominator < 0) {
            result.append("-");
        }
        long num = Math.abs((long) numerator);
        long denum = Math.abs((long) denominator);
        result.append(num / denum);
        if (num % denum == 0) {
            return result.toString();
        }

        long remainder = num % denum;
        Map<Long, Integer> map = new HashMap<>();
        result.append(".");
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                result.insert(map.get(remainder), "(");
                result.append(")");
                break;
            }
            map.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / denum);
            remainder %= denum;
        }
        return result.toString();
    }
}
