package com.leetcode.hard;

import java.util.*;

/**
 * 给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。
 *
 * MK 平均值 按照如下步骤计算：
 *
 * 如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。
 * 从这个容器中删除最小的 k 个数和最大的 k 个数。
 * 计算剩余元素的平均值，并 向下取整到最近的整数 。
 * 请你实现 MKAverage 类：
 *
 * MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。
 * void addElement(int num) 往数据流中插入一个新的元素 num 。
 * int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/finding-mk-average
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1825_W {
    int m;
    int k;
    int midLen;
    int size = 0;
    long midSum = 0;
    LinkedList<Integer> list = new LinkedList<>();
    // MAX PART, is a min heap
    PriorityQueue<Integer> maxPart = new PriorityQueue<>();
    // MIN PART, is a max heap
    PriorityQueue<Integer> minPart = new PriorityQueue<>((o1, o2) -> o2 - o1);
    // MID PART, min heap
    PriorityQueue<Integer> midPartMIN = new PriorityQueue<>();
    // MID PART, max heap
    PriorityQueue<Integer> midPartMAX = new PriorityQueue<>((o1, o2) -> o2 - o1);

    public void MKAverage(int m, int k) { // 想到了使用优先级队列但是没有想到中间部分也要用两个优先级队列 自己解法均超时
        this.m = m;
        this.k = k;
        this.midLen = m - (k << 1); // at least one
    }

    public void addElement(int num) {
        // remove oldest one first
        if (size == m) {
            Integer removal = list.removeLast();
            if (removal > midPartMAX.peek()) {
                maxPart.remove(removal);
            } else if (removal < midPartMIN.peek()) {
                minPart.remove(removal);
            } else {
                midPartMIN.remove(removal);
                midPartMAX.remove(removal);
                midSum -= removal;
            }
        } else {
            size++;
        }
        list.offerFirst(num);
        if (maxPart.size() > 0 && num > maxPart.peek()) { // must add to max part
            maxPart.offer(num);
            if (maxPart.size() > k)
                maxToMid();
        } else if (minPart.size() > 0 && num < minPart.peek()) { // must add to min part
            minPart.offer(num);
            if (minPart.size() > k)
                minToMid();
        } else { // num 属于中间范围
            midPartMIN.offer(num);
            midPartMAX.offer(num);
            midSum += num;
        }
        if (midPartMIN.size() > midLen) {
            if (minPart.size() < k)
                midToMin();
            else
                midToMax(); // 为什么else就添加到maxPart呢？
        }
    }

    public int calculateMKAverage() {
        if (size < m) {
            return -1;
        }
        return (int) (midSum / midLen);
    }

    /**
     * Move element from MAX to MID
     */
    private void maxToMid() {
        Integer toMid = maxPart.poll();
        midPartMAX.offer(toMid);
        midPartMIN.offer(toMid);
        midSum += toMid;
    }

    /**
     * Move element from MIN to MID
     */
    private void minToMid() {
        Integer toMid = minPart.poll();
        midPartMAX.offer(toMid);
        midPartMIN.offer(toMid);
        midSum += toMid;
    }

    /**
     * Move element to MAX part
     */
    private void midToMax() {
        Integer toMax = midPartMAX.poll();
        midPartMIN.remove(toMax);
        maxPart.offer(toMax);
        midSum -= toMax;
    }

    /**
     * Move element to MIN part
     */
    private void midToMin() {
        Integer toMin = midPartMIN.poll();
        midPartMAX.remove(toMin);
        minPart.offer(toMin);
        midSum -= toMin;
    }
//    链接：https://leetcode-cn.com/problems/finding-mk-average/solution/jian-dan-yi-dong-de-dui-jie-fa-dong-tai-si0yu/


// 另解法   链接：https://leetcode-cn.com/problems/finding-mk-average/solution/shi-yong-treemap-shi-jian-olognzhi-xing-ure8m/
    }
/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */

