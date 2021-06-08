package com.leetcode.medium.review;
/**
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-stack-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

import java.util.Stack;

public class LeetCode946 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> s = new Stack<>();
        int i = 0;
        for (int j = 0; j < pushed.length; j++) {
            s.push(pushed[j]);
            while (!s.empty() && pushed[j] == popped[i]) {
                s.pop();
                ++i;
            }
        }
        return s.empty();
    }
}
