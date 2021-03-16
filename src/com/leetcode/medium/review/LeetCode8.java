/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 *
 * @since 2020-04-26
 */
public class LeetCode8 {
    public enum State {
        START,
        SIGN,
        NUMBER,
        END,
    }

    boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    public int myAtoi(String str) {
        int number = 0;
        boolean isNeg = false;
        State state = State.START;
        for (char ch : str.toCharArray()) {

            switch (state) {
                case START:
                    if (ch == ' ') {
                        continue;
                    }
                    if (isDigit(ch)) {
                        number = number * 10 + ch - '0';
                        state = State.NUMBER;
                    } else if (ch == '-' || ch == '+') {
                        state = State.SIGN;
                        isNeg = ch == '-' ? true : false;
                    } else {
                        return 0;
                    }
                    break;
                case SIGN:
                    if (isDigit(ch)) {
                        number = number * 10 + ch - '0';
                        state = State.NUMBER;
                    } else {
                        return 0;
                    }
                    break;
                case NUMBER:
                    if (isDigit(ch)) {
                        if (number > (Integer.MAX_VALUE - ch - '0') / 10) { // 防止越界的处理
                            return isNeg ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                        }
                        number = number * 10 + ch - '0';
                        state = State.NUMBER;
                    } else {
                        state = State.END;
                        break;
                    }
                default:
                    break;
            }
        }
        return isNeg ? 0 - number : number;
    }

    public int myAtoin(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            // 去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            // 遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative ? -ans : ans;
    }
}

// 官方解法 状态表如下
// 	        ' '	   +/-	   number	    other
//start	    start  signed  in_number	end
//signed	end	   end	   in_number	end
//in_number	end	   end	   in_number	end
//end	    end    end	   end	        end
// class Solution {
//    public int myAtoi(String str) {
//        Automaton automaton = new Automaton();
//        int length = str.length();
//        for (int i = 0; i < length; ++i) {
//            automaton.get(str.charAt(i));
//        }
//        return (int) (automaton.sign * automaton.ans);
//    }
//}
//
//class Automaton {
//    public int sign = 1;
//    public long ans = 0;
//    private String state = "start";
//    private Map<String, String[]> table = new HashMap<String, String[]>() {{
//        put("start", new String[]{"start", "signed", "in_number", "end"});
//        put("signed", new String[]{"end", "end", "in_number", "end"});
//        put("in_number", new String[]{"end", "end", "in_number", "end"});
//        put("end", new String[]{"end", "end", "end", "end"});
//    }};
//
//    public void get(char c) {
//        state = table.get(state)[get_col(c)];
//        if ("in_number".equals(state)) {
//            ans = ans * 10 + c - '0';
//            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
//        } else if ("signed".equals(state)) {
//            sign = c == '+' ? 1 : -1;
//        }
//    }
//
//    private int get_col(char c) {
//        if (c == ' ') {
//            return 0;
//        }
//        if (c == '+' || c == '-') {
//            return 1;
//        }
//        if (Character.isDigit(c)) {
//            return 2;
//        }
//        return 3;
//    }
//}
