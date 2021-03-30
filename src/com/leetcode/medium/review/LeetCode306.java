package com.leetcode.medium.review;

/**
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 *
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode306 {
    int record = 0;
    public boolean isAdditiveNumber(String num) {
        int num1 = 0;
        boolean res = false;
        for (int i = 0; i < num.length(); i++) {
            num1 = num1 * 10 +  num.charAt(i) - '0';
            int num2 = 0;
            for (int j = i + 1; j < num.length(); j++) {
                num2 = num2 * 10 +  num.charAt(j) - '0';
                if (backtrack(num, j + 1, num1, num2)) {
                    return true;
                }
                if (num2 == 0) {
                    break;
                }
            }
            if (num1 == 0) {
                break;
            }
        }
        return res;
    }

    boolean backtrack(String num, int start, int num1, int num2) {
        if (start == num.length()) {
            System.out.println("###递归到达终止条件！" + "[" + record +", " + num1 + ", " + num2 + "]");
            return (record + num1) == num2;
        }

        int total = 0;
        for (int i = start; i < num.length(); i++) {
            total = total * 10 + num.charAt(i) - '0';
            System.out.println("递归前，" + "num1:" + num1 + ", num2:" + num2 + ", total:" + total);
            if (num1 + num2 == total) {
                System.out.println("~~~~~~~~~~~~~");
                record = num1;
                if (backtrack(num, i + 1, num2, total)) {
                    return true;
                }
            }
            if (total == 0) {
                break;
            }
        }
        return false;
    }
}
