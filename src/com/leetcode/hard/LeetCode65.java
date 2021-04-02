/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.hard;

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

// 官方解法
// class Solution {
//    public boolean isNumber(String s) {
//        Map<State, Map<CharType, State>> transfer = new HashMap<State, Map<CharType, State>>();
//        Map<CharType, State> initialMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
//            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
//        }};
//        transfer.put(State.STATE_INITIAL, initialMap);
//        Map<CharType, State> intSignMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
//        }};
//        transfer.put(State.STATE_INT_SIGN, intSignMap);
//        Map<CharType, State> integerMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
//            put(CharType.CHAR_EXP, State.STATE_EXP);
//            put(CharType.CHAR_POINT, State.STATE_POINT);
//        }};
//        transfer.put(State.STATE_INTEGER, integerMap);
//        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
//            put(CharType.CHAR_EXP, State.STATE_EXP);
//        }};
//        transfer.put(State.STATE_POINT, pointMap);
//        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
//        }};
//        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);
//        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
//            put(CharType.CHAR_EXP, State.STATE_EXP);
//        }};
//        transfer.put(State.STATE_FRACTION, fractionMap);
//        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
//        }};
//        transfer.put(State.STATE_EXP, expMap);
//        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//        }};
//        transfer.put(State.STATE_EXP_SIGN, expSignMap);
//        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
//            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
//        }};
//        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);
//
//        int length = s.length();
//        State state = State.STATE_INITIAL;
//
//        for (int i = 0; i < length; i++) {
//            CharType type = toCharType(s.charAt(i));
//            if (!transfer.get(state).containsKey(type)) {
//                return false;
//            } else {
//                state = transfer.get(state).get(type);
//            }
//        }
//        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
//    }
//
//    public CharType toCharType(char ch) {
//        if (ch >= '0' && ch <= '9') {
//            return CharType.CHAR_NUMBER;
//        } else if (ch == 'e' || ch == 'E') {
//            return CharType.CHAR_EXP;
//        } else if (ch == '.') {
//            return CharType.CHAR_POINT;
//        } else if (ch == '+' || ch == '-') {
//            return CharType.CHAR_SIGN;
//        } else {
//            return CharType.CHAR_ILLEGAL;
//        }
//    }
//
//    enum State {
//        STATE_INITIAL,
//        STATE_INT_SIGN,
//        STATE_INTEGER,
//        STATE_POINT,
//        STATE_POINT_WITHOUT_INT,
//        STATE_FRACTION,
//        STATE_EXP,
//        STATE_EXP_SIGN,
//        STATE_EXP_NUMBER,
//        STATE_END, // 这个end状态其实是没用的
//    }
//
//    enum CharType {
//        CHAR_NUMBER,
//        CHAR_EXP,
//        CHAR_POINT,
//        CHAR_SIGN,
//        CHAR_ILLEGAL,
//    }
//}
