package com.leetcode.hard.stringProcess;

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
public class LeetCode224 { // #772 题一样
    private int i = 0;

    public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        char preOperator = '+';
        for (; i < s.length(); i++) {
            char value = s.charAt(i);
            if (Character.isDigit(value)) {
                num = num * 10 + (value - '0');
            }
            if (value == '(') {
                i++;
                num = calculate(s);
            }
            if ((!Character.isDigit(value) && value != ' ') || i == s.length() - 1) {
                //当遇到了新的运算符，就要对上一个运算符sign和累计的数字num作运算
                //空格直接无视，i继续前进
                //遇到字符串末尾，肯定是要结算的
                int pre = 0;
                switch (preOperator) {
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
                    default:
                }
                preOperator = value;
                num = 0;
            }
            if (value == ')') { // 注意这里索引i不用处理
                break;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}

// 双栈解法 https://leetcode-cn.com/problems/basic-calculator/solution/ji-ben-ji-suan-qi-by-leetcode-solution-jvir/
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

// 官方解法 https://leetcode-cn.com/problems/basic-calculator/solution/ji-ben-ji-suan-qi-by-leetcode-solution-jvir/
//  public int calculate(String s) {
//        Deque<Integer> ops = new LinkedList<Integer>();
//        ops.push(1);
//        int sign = 1;
//
//        int ret = 0;
//        int n = s.length();
//        int i = 0;
//        while (i < n) {
//            if (s.charAt(i) == ' ') {
//                i++;
//            } else if (s.charAt(i) == '+') {
//                sign = ops.peek();
//                i++;
//            } else if (s.charAt(i) == '-') {
//                sign = -ops.peek();
//                i++;
//            } else if (s.charAt(i) == '(') {
//                ops.push(sign);
//                i++;
//            } else if (s.charAt(i) == ')') {
//                ops.pop();
//                i++;
//            } else {
//                long num = 0;
//                while (i < n && Character.isDigit(s.charAt(i))) {
//                    num = num * 10 + s.charAt(i) - '0';
//                    i++;
//                }
//                ret += sign * num;
//            }
//        }
//        return ret;
//