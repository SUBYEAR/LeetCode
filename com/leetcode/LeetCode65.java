/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 验证给定的字符串是否可以解释为十进制数字。
 * 例如:
 * "0" => true
 * " 0.1 " => true
 * "abc" => false
 * "1 a" => false
 * "2e10" => true
 * " -90e3   " => true
 * " 1e" => false
 * "e3" => false
 * " 6e-1" => true
 * " 99e2.5 " => false
 * "53.5e93" => true
 * " --6 " => false
 * "-+3" => false
 * "95a54e53" => false
 * 说明: 我们有意将问题陈述地比较模糊。在实现代码之前，你应当事先思考所有可能的情况。这里给出一份可能存在于有效十进制数字中的字符列表：
 * 数字 0-9
 * 指数 - "e"
 * 正/负号 - "+"/"-"
 * 小数点 - "."
 *
 * @since 2020-05-19
 */
public class LeetCode65 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        // 标记是否遇到相应情况
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] str = s.trim().toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                numSeen = true;
            } else if (str[i] == '.') {
                // .之前不能出现.或者e
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (str[i] == 'e' || str[i] == 'E') {
                // e之前不能出现e，必须出现数
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
                numSeen = false;// 重置numSeen，排除123e或者123e+的情况,确保e之后也出现数
            } else if (str[i] == '-' || str[i] == '+') {
                // +-出现在0位置或者e/E的后面第一个位置才是合法的
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false;
                }
            } else {// 其他不合法字符
                return false;
            }
        }
        return numSeen;
    }

    public boolean isNumber1(String s) {
        String str = "^[+|-]?((\\d+\\.?)|(\\d*\\.\\d+))([E|e][+|-]?\\d+)?$";
        return s.trim().matches(str);
    }
}
