package com.leetcode.medium.review.dfs;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/decode-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode394 {
    int index;
    public String decodeString(String s) {
        // 遍历这个栈：
        //如果当前的字符为数位，解析出一个数字（连续的多个数位）并进栈
        //如果当前的字符为字母或者左括号，直接进栈
        //如果当前的字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，
        //此时取出栈顶的数字（此时栈顶一定是数字，想想为什么？），就是这个字符串应该出现的次数，
        //我们根据这个次数和字符串构造出新的字符串并进栈
        //重复如上操作，最终将栈中的元素按照从栈底到栈顶的顺序拼接起来，
        // 就得到了答案。注意：这里可以用不定长数组来模拟栈操作，方便从栈底向栈顶遍历。
        LinkedList<String> stack = new LinkedList<>();
        index = 0;
        while (index < s.length()) {
            char curChar = s.charAt(index);
            if (Character.isDigit(curChar)) {
                String digits = getDigits(s);
                stack.addLast(digits);
            } else if (Character.isLetter(curChar) || curChar == '[') {
                stack.addLast(String.valueOf(curChar));
                ++index;
            } else {
                ++index;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stack.peekLast())) {
                    sub.addLast(stack.removeLast());
                }
                Collections.reverse(sub);

                stack.removeLast(); // 左括号出栈

                int times = Integer.parseInt(stack.removeLast()); // 重复次数
                StringBuffer temp =  new StringBuffer();
                String o = getString(sub);
                while (times-- > 0) {
                    temp.append(o);
                }
                stack.addLast(temp.toString());

            }
        }

        return getString(stack);
    }

    private String getDigits(String s) {
        StringBuffer  result = new StringBuffer ();
        while (Character.isDigit(s.charAt(index))) {
            result.append(s.charAt(index));
            ++index;
        }
        return result.toString();
    }

    private String getString(LinkedList<String> lists) {
        StringBuffer  result = new StringBuffer ();
        for (String s : lists) {
            result.append(s);
        }
        return result.toString();
    }
}

// 递归解法
// class Solution {
//    String src;
//    int ptr;
//
//    public String decodeString(String s) {
//        src = s;
//        ptr = 0;
//        return getString();
//    }
//
//    public String getString() {
//        if (ptr == src.length() || src.charAt(ptr) == ']') {
//            // String -> EPS
//            return "";
//        }
//
//        char cur = src.charAt(ptr);
//        int repTime = 1;
//        String ret = "";
//
//        if (Character.isDigit(cur)) {
//            // String -> Digits [ String ] String
//            // 解析 Digits
//            repTime = getDigits();
//            // 过滤左括号
//            ++ptr;
//            // 解析 String
//            String str = getString();
//            // 过滤右括号
//            ++ptr;
//            // 构造字符串
//            while (repTime-- > 0) {
//                ret += str;
//            }
//        } else if (Character.isLetter(cur)) {
//            // String -> Char String
//            // 解析 Char
//            ret = String.valueOf(src.charAt(ptr++));
//        }
//
//        return ret + getString();
//    }
//
//    public int getDigits() {
//        int ret = 0;
//        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
//            ret = ret * 10 + src.charAt(ptr++) - '0';
//        }
//        return ret;
//    }
//}
//
