package com.leetcode.hard.greedy;

import java.util.PriorityQueue;

/**
 * 给你一个整数数组 target 。一开始，你有一个数组 A ，它的所有元素均为 1 ，你可以执行以下操作：
 *
 * 令 x 为你数组里所有元素的和
 * 选择满足 0 <= i < target.size 的任意下标 i ，并让 A 数组里下标为 i 处的值为 x 。
 * 你可以重复该过程任意次
 * 如果能从 A 开始构造出目标数组 target ，请你返回 True ，否则返回 False 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-target-array-with-multiple-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1354_WW {
    public boolean isPossible(int[] target) {
        if (target.length == 1 && target[0] != 1) {
            return false;
        }

        // 当前轮最大的数是s(其实等于上一轮所有数的总和), 总和是sum, 那么 上一轮中被替换的数是s-(sum-s)
        // sum-s是除最大值以外的所有数的和
        long tot = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> o2.intValue() - o1.intValue()); // 大顶堆
        for (int val : target) { // 从结束状态往开始状态推，确认最后是否能得到n个1
            tot += val;
            pq.add((long)val);
        }
        while (!pq.isEmpty()) {
            long x = pq.poll();
            if ((x == 1)) {
                break;
            }
            if (2 * x - tot < 1) {
                return false;
            }
            long y = pq.peek(); // y是第二小的值
            long left = tot - x; // 如果最大的数被替换掉以后还是最大的数，那么每次减去的差值，即 (sum-s) 是恒定不变的
            long k = 0; // 算出K的目的是让y成为最大的数
            if (y == 1) {
                k = (x - y + left - 1) / left;
            } else {
                k = (x - y) / left + 1;
            }
            x -= k * left;
            if (x <= 0) {
                return false;
            }
            tot -= k * left; // 更新total
            pq.add(x);
        }
        return true;
    }
}
