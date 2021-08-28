package com.leetcode.hard;

import java.util.PriorityQueue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode295 {
    PriorityQueue<Integer> pq0, pq1;
    /** initialize your data structure here. */
    public LeetCode295() {
        pq0 = new PriorityQueue<>((o1, o2) -> o2 - o1);
        pq1 = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (pq0.size() == pq1.size()) {
            if (pq0.size() == 0) {
                pq0.add(num);
            } else {
                int maxVal = pq0.peek();
                if (num <= maxVal) {
                    pq0.add(num);
                } else {
                    pq1.add(num);
                }
            }
        } else {
            int peek = pq0.size() > pq1.size() ? pq0.poll() : pq1.poll();
            int max = Math.max(peek, num);
            int min = Math.min(peek, num);
            pq1.add(max);
            pq0.add(min);
        }
    }

    public double findMedian() {
        if (pq0.size() == pq1.size()) {
            return (pq0.peek() + pq1.peek()) / 2D;
        }
        return pq0.size() > pq1.size() ? pq0.peek() : pq1.peek();
    }
}
