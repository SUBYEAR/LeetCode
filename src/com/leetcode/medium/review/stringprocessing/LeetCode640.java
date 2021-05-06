package com.leetcode.medium.review.stringprocessing;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
 *
 * 如果方程没有解，请返回“No solution”。
 *
 * 如果方程有无限解，则返回“Infinite solutions”。
 *
 * 如果方程中只有一个解，要保证返回值 x 是一个整数。
 *
 * 示例 1：
 *
 * 输入: "x+5-3+x=6+x-2"
 * 输出: "x=2"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/solve-the-equation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode640 {
    public String solveEquation(String equation) {
        int eq = equation.indexOf('=');
        String left = equation.substring(0, eq);
        String right = equation.substring(eq + 1);
        Deque<Integer> varLeft = new LinkedList<>();
        Deque<Integer> numLeft = new LinkedList<>();
        Deque<Integer> varRight = new LinkedList<>();
        Deque<Integer> numRight = new LinkedList<>();
        test(left, varLeft, numLeft);
        test(right, varRight, numRight);
        Integer a = varLeft.stream().reduce(Integer::sum).orElse(0);
        Integer b = numLeft.stream().reduce(Integer::sum).orElse(0);
        Integer c = varRight.stream().reduce(Integer::sum).orElse(0);
        Integer d = numRight.stream().reduce(Integer::sum).orElse(0);
        if ((a - c == 0) && (d - b == 0)) {
            return "Infinite solutions";
        } else if ((a - c == 0) && (d - b != 0) || ((d - b) % (a - c) != 0)) {
            return "No solution";
        }
        return "x="+(d - b) / (a - c);
    }

    void test(String s, Deque<Integer> var, Deque<Integer> num) {
        int len = s.length();
        char lastOp = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                int temp = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    temp = temp * 10 + s.charAt(i) - '0';
                    i++;
                }
                if (i < len && s.charAt(i) == 'x') {
                    addNum(lastOp, var, temp);
                } else {
                    addNum(lastOp, num, temp);
                    --i;
                }
            } else if (s.charAt(i) == 'x') {
                addNum(lastOp, var, 1);
            } else {
                lastOp = s.charAt(i);
            }
        }
    }

    void addNum(char op, Deque<Integer> st, int num) {
        if (op == '+') st.push(num);
        else  st.push(-num);
    }
}

// 官方解法
// public String coeff(String x) {
//        if (x.length() > 1 && x.charAt(x.length() - 2) >= '0' && x.charAt(x.length() - 2) <= '9')
//            return x.replace("x", "");
//        return x.replace("x", "1");
//    }
//    public String solveEquation(String equation) {
//        String[] lr = equation.split("=");
//        int lhs = 0, rhs = 0;
//        for (String x: breakIt(lr[0])) {
//            if (x.indexOf("x") >= 0) {
//                lhs += Integer.parseInt(coeff(x));
//            } else
//                rhs -= Integer.parseInt(x);
//        }
//        for (String x: breakIt(lr[1])) {
//            if (x.indexOf("x") >= 0)
//                lhs -= Integer.parseInt(coeff(x));
//            else
//                rhs += Integer.parseInt(x);
//        }
//        if (lhs == 0) {
//            if (rhs == 0)
//                return "Infinite solutions";
//            else
//                return "No solution";
//        }
//        return "x=" + rhs / lhs;
//    }
//    public List < String > breakIt(String s) { // 将2x + 3 拆分成2x 和 +3 两个部分用list保存
//        List < String > res = new ArrayList < > ();
//        String r = "";
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '+' || s.charAt(i) == '-') {
//                if (r.length() > 0)
//                    res.add(r);
//                r = "" + s.charAt(i);
//            } else
//                r += s.charAt(i);
//        }
//        res.add(r);
//        return res;
//    }
