package com.leetcode.medium.review;

/**
 * 请你设计一个支持下述操作的栈。
 *
 * 实现自定义栈类 CustomStack ：
 *
 * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
 * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
 * int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
 * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-a-stack-with-increment-operation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1381 {
    int[] stack;
    int index;

    public LeetCode1381(int maxSize) {
        stack = new int[maxSize];
        index = -1;
    }

    public void push(int x) {
        ++index;
        if (index < stack.length) {
            stack[index] = x;
        } else {
            index = stack.length - 1;
        }
    }

    public int pop() {
        if (index == -1) {
            return -1;
        }
        int res = stack[index];
        stack[index] = 0;
        --index;
        return res;
    }

    public void increment(int k, int val) {
        for (int i = 0; i < Math.min(k, index + 1); i++) {
            stack[i] += val;
        }
    }
}
