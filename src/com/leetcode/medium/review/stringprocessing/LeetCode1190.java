package com.leetcode.medium.review.stringprocessing;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1190 {
    // 这种使用栈来处理字符的在LeetCode394中也用到了
    public String reverseParentheses(String s) {
//        StringBuilder stack = new StringBuilder();
//        int i = 0;
//        while (i < s.length()) {
//            char c = s.charAt(i);
//            if (Character.isLetter(c) || c == '(') {
//                stack.append(c);
//            } else {
//                String tmp = "";
//                while (stack.length() != 0 && stack.charAt(stack.length() - 1) != '(') {
//                    tmp += stack.charAt(stack.length() - 1);
//                    stack.deleteCharAt(stack.length() - 1);
//                }
//                stack.deleteCharAt(stack.length() - 1);
//                stack.append(tmp);
//            }
//            i++;
//        }
//        return stack.toString();

        Deque<String> stack = new LinkedList<String>();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (ch == ')') {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
