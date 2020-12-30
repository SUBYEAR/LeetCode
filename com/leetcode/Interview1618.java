/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 你有两个字符串，即pattern和value。
 * pattern字符串由字母"a"和"b"组成，用于描述字符串中的模式。例如，字符串"catcatgocatgo"匹配模式"aabab"
 * （其中"cat"是"a"，"go"是"b"），该字符串也匹配像"a"、"ab"和"b"这样的模式。但需注意"a"和"b"不能同时表示相同的字符串。
 * 编写一个方法判断value字符串是否匹配pattern字符串。
 * 示例 1：
 * 输入： pattern = "abba", value = "dogcatcatdog"
 * 输出： true
 *
 * @since 2020-06-22
 */
public class Interview1618 {
    public boolean patternMatching(String pattern, String value) {
        int count_a = 0, count_b = 0;
        for (char ch : pattern.toCharArray()) {
            if (ch == 'a') {
                ++count_a;
            } else {
                ++count_b;
            }
        }

        // a模式表示出现次数多的那一个
        if (count_a < count_b) {
            int temp = count_a;
            count_a = count_b;
            count_b = temp;
            char[] array = pattern.toCharArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] == 'a' ? 'b' : 'a';
            }
            pattern = new String(array);
        }

        if (value.length() == 0) {
            return count_b == 0; // 只出现字母a或者value为空时count_b都会为0
        }
        if (pattern.length() == 0) {
            return false; // value不空 pattern为空
        }
        for (int len_a = 0; count_a * len_a <= value.length(); ++len_a) { // 枚举l_a(模式字符a的长度) 范围是[0, l_v / c_a]
            int rest = value.length() - count_a * len_a;
            if ((count_b == 0 && rest == 0) || (count_b != 0 && rest % count_b == 0)) {
                int len_b = (count_b == 0 ? 0 : rest / count_b);
                int pos = 0;
                boolean correct = true;
                String value_a = "", value_b = "";
                for (char ch : pattern.toCharArray()) {
                    if (ch == 'a') {
                        String sub = value.substring(pos, pos + len_a);
                        if (value_a.length() == 0) {
                            value_a = sub;
                        } else if (!value_a.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_a;
                    } else {
                        String sub = value.substring(pos, pos + len_b);
                        if (value_b.length() == 0) {
                            value_b = sub;
                        } else if (!value_b.equals(sub)) {
                            correct = false;
                            break;
                        }
                        pos += len_b;
                    }
                }
                if (correct && !value_a.equals(value_b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean patternMatchingBacktrace(String pattern, String value) {
        String s[] = new String[2];
        return solve(s, pattern, 0, value, 0);
    }

    /**
     * 回溯遍历设置a,b的对应值，尝试每一种可能。
     *
     * @param s s[0]=a对应的字符串 s[1]=b对应的字符串
     * @param pattern 模式串
     * @param index1 模式串匹配位置
     * @param value 匹配串（待匹配的字符串）
     * @param index2 匹配串匹配位置
     * @return
     */
    public boolean solve(String[] s, String pattern, int index1, String value, int index2) {
        // 匹配完成
        if (index1 == pattern.length() && index2 == value.length()) {
            return true;
        }
        // 注意匹配串匹配位置等于长度的时候也可以继续匹配，因为模式串的a，b可以匹配空串。
        if (index1 >= pattern.length() || index2 > value.length()) {
            return false;
        }
        int num = pattern.charAt(index1) - 'a';
        if (s[num] == null) {
            // 从当前尝试a或b对应的字符串的每一种可能
            for (int i = index2; i <= value.length(); i++) {
                s[num] = value.substring(index2, i);
                // (s[num]==null||s[num^1]==null||!s[num].equals(s[num^1])) [是指a，b对应的字符串不可相等]
                if ((s[num] == null || s[num ^ 1] == null || !s[num].equals(s[num ^ 1]))
                    && solve(s, pattern, index1 + 1, value, i)) {
                    return true;
                }
            }
            // 失配时应将设置过的对应字符串为空
            s[num] = null;
            return false;
        } else {
            // 若此前a或b已有对应的字符串匹配了，则查看当前位置时候能够匹配上。
            int end = index2 + s[num].length();
            if (end > value.length() || !value.substring(index2, end).equals(s[num])) {
                return false;
            }
            return solve(s, pattern, index1 + 1, value, end);
        }
    }
}
