package com.leetcode.hard.segmentTree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 我们把无限数量 ∞ 的栈排成一行，按从左到右的次序从 0 开始编号。每个栈的的最大容量 capacity 都相同。
 * <p>
 * 实现一个叫「餐盘」的类 DinnerPlates：
 * <p>
 * DinnerPlates(int capacity) - 给出栈的最大容量 capacity。
 * void push(int val) - 将给出的正整数 val 推入 从左往右第一个 没有满的栈。
 * int pop() - 返回 从右往左第一个 非空栈顶部的值，并将其从栈中删除；如果所有的栈都是空的，请返回 -1。
 * int popAtStack(int index) - 返回编号 index 的栈顶部的值，并将其从栈中删除；如果编号 index 的栈是空的，请返回 -1。
 * 提示：
 * <p>
 * 1 <= capacity <= 20000
 * 1 <= val <= 20000
 * 0 <= index <= 100000
 * 最多会对 push，pop，和 popAtStack 进行 200000 次调用。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dinner-plate-stacks
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1172_W {
    // 题目的本质就是让我们快速找到 从右往左第一个不是空的栈 与 从左往右第一个非满栈 。
    // 树状数组 tree 维护一个序列 num（支持Log复杂度单点修改/区间查询），第i个位置维护的是第i个栈中的元素个数，那么上诉两个问题我们都可以快速得到
    // 解题思路的本质是每个stack存储K个数,stack的容量变化情况用树状数组进行维护和查询
    private static final int MAXN = 200000;
    private Stack<Integer>[] stk = new Stack[MAXN + 5]; // stk的索引是基于1开始的
    int cap;
    int siz;
    BitTree bitTree = new BitTree();

    // DinnerPlates
    public LeetCode1172_W(int capacity) {
        cap = capacity;
        for (int i = 0; i < stk.length; i++) {
            stk[i] = new Stack<>();
        }
    }

    int getFree() { // 从左往右第一个非满栈 这里是用倍增法求k
        // 目标是找到一个最大的 k，使其满足 k*capacity = sum_{i=1 ~ k}_num[i],那么k + 1就是第一个没有满的栈
        int rate = 0, k = 0;
        // 类似[1,128]的
        for (int i = 14; i >= 0; i--) { // 2^14次方是16384, 是题目中给的范围20000转换为2进制的最大位数
            if (k + (1 << i) > MAXN) {
                continue;
            }
            if (rate + bitTree.sum[k + (1 << i)] == cap * (k + (1 << i))) {
                rate += bitTree.sum[k += (1 << i)]; // 这里更新了k每一次都往k的二进制表示上加一个1
            }
        }
        return k + 1;// 下标从1开始
    }

    int getRight(int cnt) { // 从右往左第一个不是空的栈
        // 餐盘栈中当前元素总数 siz, 要找到一个最小的 k，满足sum_{i=1 ~ k}_num[i] = siz, 那么这个k就是从右往左第一个非空栈
        int rate = 0, k = 0; // 找最小的 k 这个问题可以使用倍增解决
        for (int i = 14; i >= 0; i--) { // 从高到低枚举 k−1 的二进制位(因为 k 是最小的，所以 k-1 一定不满足上述条件)
            if (k + (1 << i) > MAXN) {
                continue;
            }
            if (rate + bitTree.sum[k + (1 << i)] < cnt) {
                rate += bitTree.sum[k += (1 << i)];
            }
        }
        return k + 1; // 求出来 k−1 之后，再加一就是 k 了。
    }


    // pop
    int getPop() {
        int left = 1, right = MAXN;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (bitTree.get(mid) >= siz) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // push
    int getPush() {
        int l = 1, r = MAXN;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (cap * mid > bitTree.get(mid)) r = mid;
            else l = mid + 1;
        }
        return l;
    }


//    链接：https://leetcode-cn.com/problems/dinner-plate-stacks/solution/shu-zhuang-shu-zu-er-fen-1184ms-by-n7w/


    public void push(int val) {
        int p = getFree();

        stk[p].push(val);
        ++siz;
        bitTree.add(p, 1);
    }

    public int pop() {
        if (siz == 0) {
            return -1;
        }

        int p = getRight(siz);
        int r = stk[p].pop();
        --siz;
        bitTree.add(p, -1);

        return r;
    }

    public int popAtStack(int index) {
        if (stk[index + 1].empty()) {
            return -1;
        }

        int r = stk[index + 1].pop();
        --siz;
        bitTree.add(index + 1, -1);

        return r;
    }

    class BitTree {
        int[] sum = new int[MAXN + 5];

        int lowbit(int x) {
            return x & -x;
        }

        void add(int x, int v) {
            for (int i = x; i <= MAXN; i += lowbit(i)) {
                sum[i] += v;
            }
        }

        int get(int x) {
            int ret = 0;
            for (int i = x; i > 0; i -= lowbit(i)) {
                ret += sum[i];
            }
            return ret;
        }
    }
}
// https://leetcode-cn.com/problems/dinner-plate-stacks/solution/1172-can-pan-zhan-shu-zhuang-shu-zu-shang-bei-zeng/
