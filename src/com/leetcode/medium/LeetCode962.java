package com.leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 *
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-ramp
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-23
 */
public class LeetCode962 { //单调递减栈，遍历到当前元素比栈顶表示的元素大不断出栈更新结果
    public int maxWidthRamp(int[] A) {
        int len = A.length;
        Deque<Integer> s = new LinkedList<>();
        s.push(0);
        for (int i = 1; i < len; i++) {
            if (A[i] < A[s.peek()]) {
                s.push(i);
            }
        }

        int res = 0;
        for (int i = len - 1; i >= 0; i--) {
            while (!s.isEmpty() && A[i] >= A[s.peek()]) {
                res = Math.max(res, i - s.peek());
                s.pop();
            }
        }
        return res;
    }
}
