package com.leetcode.hard;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 实现一个基本的计算器来计算一个简单的字符串表达式 s 的值。
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 *
 * 提示：
 * 1 <= s.length <= 3 * 105
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 *
 * https://leetcode-cn.com/problems/basic-calculator/
 */
public class LeetCode224 {
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

// 双栈解法
//class Solution {
//    public int calculate(String s) {
//        // 存放所有的数字
//        Deque<Integer> nums = new ArrayDeque<>();
//        // 为了防止第一个数为负数，先往 nums 加个 0
//        nums.addLast(0);
//        // 将所有的空格去掉，并将 (- 替换为 (0-
//        s = s.replaceAll(" ", "");
//        s = s.replaceAll("\\(-", "(0-");
//        // 存放所有的操作，包括 +/-
//        Deque<Character> ops = new ArrayDeque<>();
//        int n = s.length();
//        char[] cs = s.toCharArray();
//        for (int i = 0; i < n; i++) {
//            char c = cs[i];
//            if (c == '(') {
//                ops.addLast(c);
//            } else if (c == ')') {
//                // 计算到最近一个左括号为止
//                while (!ops.isEmpty()) {
//                    char op = ops.peekLast();
//                    if (op != '(') {
//                        calc(nums, ops);
//                    } else {
//                        ops.pollLast();
//                        break;
//                    }
//                }
//            } else {
//                if (isNum(c)) {
//                    int u = 0;
//                    int j = i;
//                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
//                    while (j < n && isNum(cs[j])) u = u * 10 + (int)(cs[j++] - '0');
//                    nums.addLast(u);
//                    i = j - 1;
//                } else {
//                    // 有一个新操作要入栈时，先把栈内可以算的都算了
//                    while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
//                    ops.addLast(c);
//                }
//            }
//        }
//        while (!ops.isEmpty()) calc(nums, ops);
//        return nums.peekLast();
//    }
//    void calc(Deque<Integer> nums, Deque<Character> ops) {
//        if (nums.isEmpty() || nums.size() < 2) return;
//        if (ops.isEmpty()) return;
//        int b = nums.pollLast(), a = nums.pollLast();
//        char op = ops.pollLast();
//        nums.addLast(op == '+' ? a + b : a - b);
//    }
//    boolean isNum(char c) {
//        return Character.isDigit(c);
//    }
//}



//   public int calculate(String s) {
//        Deque<Character> q=new LinkedList<>();
//        for(char c:s.toCharArray()){
//            q.offer(c);
//        }
//        return dfs(q);
//
//    }
//    public int dfs(Deque<Character> q){
//        Stack<Integer> stack=new Stack<>();
//
//        // 当前运算符的前一个运算符
//        char op='+';
//        // op前面的数
//        int num=0;
//        int res=0;
//        while(!q.isEmpty()){
//            char c=q.poll();
//            // c是数字就更新数字
//            if(Character.isDigit(c)){
//                num=num*10+c-'0';
//            }
//
//            // 左括号就进入递归
//            if(c=='('){
//                num=dfs(q);
//            }
//
//            // c是远算符
//            if(!Character.isDigit(c)&&c!=' '||q.isEmpty()){ // 后面的或条件是为了计算最后一个数字
//                if(op=='+'){
//                    stack.push(num);
//                }
//                else if(op=='-'){
//                    stack.push(-num);
//                }
//                else if(op=='*'){
//                    stack.push(stack.pop()*num);
//                }
//                else if(op=='/'){
//                    stack.push(stack.pop()/num);
//                }
//
//                num=0;
//                op=c;
//
//            }
//            // 是右括号就退出循环 直接返回结果
//            if(c==')'){
//                break;
//            }
//
//        }
//
//        //System.out.println(stack.toString());
//        for(int i:stack){
//                res+=i;
//        }
//        return res;
//
//    }


