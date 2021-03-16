package com.leetcode.hard.suggestion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 实现一个基本的计算器来计算简单的表达式字符串。
 *
 * 表达式字符串只包含非负整数，算符 +、-、*、/ ，左括号 ( 和右括号 ) 。整数除法需要 向下截断 。
 *
 * 你可以假定给定的表达式总是有效的。所有的中间结果的范围为 [-231, 231 - 1] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/basic-calculator-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode772 {
    private int index = 0;

    public int calculate(String s) {
        char[] ch = s.toCharArray();
        return cal(ch);
    }

    private int cal(char[] ch) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char sign = '+';
        for (; index < ch.length; index++) {
            char c = ch[index];
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
            }
            if (c == '(') {
                index++;//index指针指到下一个字符
                num = cal(ch);
            }
            //当遇到了新的运算符，就要对上一个运算符sign和累计的数字num作运算
            //空格直接无视，i继续前进
            //遇到字符串末尾，肯定是要结算的
            if (!Character.isDigit(c) && c != ' ' || index == ch.length - 1) {
                int pre = 0;
                switch (sign) {

                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        stack.push(pre * num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre / num);
                        break;
                }
                sign = c;
                num = 0;//计数归位
            }
            if (c == ')') break;//阶段，后面开始计算局部结果，返回
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }

}
