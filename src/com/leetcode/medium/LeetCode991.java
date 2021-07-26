package com.leetcode.medium;

import java.util.*;

/**
 * 在显示着数字的坏计算器上，我们可以执行以下两种操作：
 *
 * 双倍（Double）：将显示屏上的数字乘 2；
 * 递减（Decrement）：将显示屏上的数字减 1 。
 * 最初，计算器显示数字 X。
 *
 * 返回显示数字 Y 所需的最小操作数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/broken-calculator
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode991 {
    public int brokenCalc(int x, int y) {
        int step = 0;
        while (y > x) {
            if (y % 2 != 0) {
                y += 1;
            } else {
                y /= 2;
            }
            ++step;
        }
        return step + x - y;
    }
}
