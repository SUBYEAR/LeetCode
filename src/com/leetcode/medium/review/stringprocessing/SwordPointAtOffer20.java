package com.leetcode.medium.review.stringprocessing;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 判断字符串是否是合法的数字
 * 思路使用三个标记：数字标记(numSeen), 点号标记(dotSeen)，和科学表示符号标记(eSeen)
 *
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */

public class SwordPointAtOffer20 {
    public boolean isNumber(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        //标记是否遇到相应情况
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] str = s.trim().toCharArray();
        for (int i = 0; i < str.length; i++) {
            if (str[i] >= '0' && str[i] <= '9') {
                numSeen = true;
            } else if (str[i] == '.') {
                //.之前不能出现.或者e
                if (dotSeen || eSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (str[i] == 'e' || str[i] == 'E') {
                //e之前不能出现e，必须出现数
                if (eSeen || !numSeen) {
                    return false;
                }
                eSeen = true;
                numSeen = false;//重置numSeen，排除123e或者123e+的情况,确保e之后也出现数
            } else if (str[i] == '-' || str[i] == '+') {
                //+-出现在0位置或者e/E的后面第一个位置才是合法的
                if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E') {
                    return false;
                }
            } else {//其他不合法字符
                return false;
            }
        }
        return numSeen;
    }
}

