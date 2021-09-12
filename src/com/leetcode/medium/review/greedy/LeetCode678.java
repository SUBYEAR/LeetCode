package com.leetcode.medium.review.greedy;

import java.util.LinkedList;

/**
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 *
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parenthesis-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode678 {
    public boolean checkValidString(String s) {
        int len = s.length();
        StringBuilder sb1 = new StringBuilder(len);
        LinkedList<Integer> pos1 = new LinkedList<>();

        LinkedList<Integer> pos2 = new LinkedList<>();
        StringBuilder sb2 = new StringBuilder(len);
        int index = 0;
        while (index < len) {
            char ch = s.charAt(index);
            if (ch == '*') {
                sb2.append(ch);
                pos2.addLast(index);
            } else if (ch == '(') {
                sb1.append(ch);
                pos1.addLast(index);
            } else {
                if (sb1.length() != 0) {
                    sb1.deleteCharAt(sb1.length() - 1);
                    pos1.removeLast();
                    index++;
                    continue;
                }
                if (sb2.length() == 0) {
                    return false;
                }
                sb2.deleteCharAt(sb2.length() - 1);
                pos2.removeLast();
            }
            index++;
        }
        while (pos1.size() > 0 && pos2.size() > 0) {
            if (pos2.getLast() < pos1.getLast()) {
                return false;
            }
            pos1.removeLast();
            pos2.removeLast();
        }
        return pos1.size() == 0;
    }
}
