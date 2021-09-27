package com.leetcode.medium.passed.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 我们将整数 x 的 权重 定义为按照下述规则将 x 变成 1 所需要的步数：
 * <p>
 * 如果 x 是偶数，那么 x = x / 2
 * 如果 x 是奇数，那么 x = 3 * x + 1
 * 比方说，x=3 的权重为 7 。因为 3 需要 7 步变成 1 （3 --> 10 --> 5 --> 16 --> 8 --> 4 --> 2 --> 1）。
 * <p>
 * 给你三个整数 lo， hi 和 k 。你的任务是将区间 [lo, hi] 之间的整数按照它们的权重 升序排序 ，
 * 如果大于等于 2 个整数有 相同 的权重，那么按照数字自身的数值 升序排序 。
 * <p>
 * 请你返回区间 [lo, hi] 之间的整数按权重排序后的第 k 个数。
 * <p>
 * 注意，题目保证对于任意整数 x （lo <= x <= hi） ，它变成 1 所需要的步数是一个 32 位有符号整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sort-integers-by-the-power-value
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1387 {
    public int getKth(int lo, int hi, int k) {
        int cnt = hi - lo  + 2 - k;
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(cnt, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return compareWeight(o1, o2);
            }
        });

        for (int i = lo; i <= hi; i++) {
            q.offer(i);
        }
        for (int i = 0; i < k - 1; i++) {
            q.poll();
        }

        return q.peek();
    }

    int getStep(int x) {
        int step = 0;
        while (x != 1) {
            ++step;
            if (x % 2 == 0) {
                x /= 2;
            } else {
                x = 3 * x + 1;
            }
        }
        return step;
    }

    int compareWeight(int o1, int o2) {
        if (getStep(o1) == getStep(o2)) {
            return o1 - o2;
        } else {
            return getStep(o1) - getStep(o2);
        }
    }
}
