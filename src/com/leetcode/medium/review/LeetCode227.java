package com.leetcode.medium.review;

import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分
 *
 * @since 2021-03-03
 */
public class LeetCode227 {
	public int calculate(String s) {
		Stack<Integer> st = new Stack<>();
		char[] array = s.toCharArray();
		char last = '+';
		for (int i = 0; i < array.length; i++) {
			if (array[i] == ' ') {
				continue;
			}

			if (Character.isDigit(array[i])) { // 数字中间肯定没有空格
				int temp = array[i] - '0';
				while (++i < s.length() && Character.isDigit(array[i])) {
					temp = temp * 10 + array[i] - '0';
				}
				--i;

				if(last == '+') st.push(temp);
				else if(last == '-') st.push(-temp);
				else st.push(res(last, st.pop(), temp));
			} else {
				last = array[i];
			}
		}
		int ans = 0;
		for(int num : st) ans += num;
		return ans;
	}

	private int res(char op, int a, int b){
		if(op == '*') return a * b;
		else  return a / b;
	}

}
