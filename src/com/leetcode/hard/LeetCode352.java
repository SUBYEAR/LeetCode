package com.leetcode.hard;

import java.util.LinkedList;

/**
 * 给定一个非负整数的数据流输入 a1，a2，…，an，…，将到目前为止看到的数字总结为不相交的区间列表。
 *
 * 例如，假设数据流中的整数为 1，3，7，2，6，…，每次的总结为：
 *
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/data-stream-as-disjoint-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode352 {
//    ArrayList<int[]> list;
    // 下面两个链表里面的元素肯定是没有重复的
    LinkedList<Integer> startPoints;
    LinkedList<Integer> endPoints;
    /** Initialize your data structure here. */
    public LeetCode352() {
        startPoints = new LinkedList<>();
        endPoints = new LinkedList<>();
    }

    public void addNum(int val) {
        if (startPoints.isEmpty()) {
            startPoints.add(val);
            endPoints.add(val);
            return;
        }

        if (startPoints.contains(val) || endPoints.contains(val)) { // 边界点上
            return;
        }

        int endIdx = lowBound(endPoints, val);
        int startIdx = lowBound(startPoints, val);
        if (startIdx == endIdx + 1) { // 这个值在某个区间中
            return; // 不用进行任何操作
        }

        // 处理合并的逻辑
        if (endIdx == 0) {
            if (val == (startPoints.getFirst() - 1)) {
                startPoints.removeFirst();
                startPoints.addFirst(val);
            } else {
                startPoints.addFirst(val);
                endPoints.addFirst(val);
            }
            return;
        }

        if (endIdx == endPoints.size()) {
            if (val == (endPoints.getLast() + 1)) {
                endPoints.removeLast();
                endPoints.addLast(val);
            } else {
                startPoints.addLast(val);
                endPoints.addLast(val);
            }
            return;
        }

        Integer[] left = new Integer[] {startPoints.get(endIdx - 1), endPoints.get(endIdx - 1)};
        Integer[] right = new Integer[] {startPoints.get(endIdx), endPoints.get(endIdx)};

        if ((left[1] + 1 == val) && (val == right[0] - 1)) {
            startPoints.remove(endIdx);
            endPoints.remove(endIdx);
            endPoints.set(endIdx - 1, right[1]);
        } else if (left[1] + 1 == val) {
            endPoints.set(endIdx - 1, val);
        } else if (val == right[0] - 1) {
            startPoints.set(endIdx, val);
        } else {
            startPoints.add(endIdx, val);
            endPoints.add(endIdx, val);
        }
    }

    public int[][] getIntervals() {
        int size = startPoints.size();
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            ans[i] = new int[] {startPoints.get(i), endPoints.get(i)};
        }
        return ans;
    }

    int lowBound(LinkedList<Integer> nums, int val) {
        int lo = 0;
        int hi = nums.size();
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (val <= nums.get(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
//
//    int upperBound(List<Integer> nums, int val) {
//        int lo = 0;
//        int hi = nums.size();
//        while (lo < hi) {
//            int mid = (lo + hi) / 2;
//            if (val < nums.get(mid)) {
//                hi = mid;
//            } else {
//                lo = mid + 1;
//            }
//        }
//        return lo;
}
