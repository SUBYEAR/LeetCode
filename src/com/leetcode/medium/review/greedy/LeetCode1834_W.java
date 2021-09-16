package com.leetcode.medium.review.greedy;

import java.util.*;

/**
 * 给你一个二维数组 tasks ，用于表示 n​​​​​​ 项从 0 到 n - 1 编号的任务。
 * 其中 tasks[i] = [enqueueTimei, processingTimei] 意味着第 i 项任务将会于 enqueueTimei 时进入任务队列，需要 processingTimei 的时长完成执行。
 *
 * 现有一个单线程 CPU ，同一时间只能执行 最多一项 任务，该 CPU 将会按照下述方式运行：
 *
 * 如果 CPU 空闲，且任务队列中没有需要执行的任务，则 CPU 保持空闲状态。
 * 如果 CPU 空闲，但任务队列中有需要执行的任务，则 CPU 将会选择 执行时间最短 的任务开始执行。如果多个任务具有同样的最短执行时间，则选择下标最小的任务开始执行。
 * 一旦某项任务开始执行，CPU 在 执行完整个任务 前都不会停止。
 * CPU 可以在完成一项任务后，立即开始执行一项新任务。
 * 返回 CPU 处理任务的顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-threaded-cpu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1834_W {
    // 如果按照时刻递增1的方法遍历会超时
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;

        List<Integer> idx = new ArrayList<>(); // 离散化
        for (int i = 0; i < n; i++) idx.add(i);
        idx.sort(Comparator.comparingInt(o -> tasks[o][0]));

        Queue<int[]> heap = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
            return Integer.compare(o1[1], o2[1]);
        });

        int[] res = new int[n];
        int k = 0, time = 0;
        for (int i = 0; i < n; i++) {
            if (heap.isEmpty())
                time = Math.max(time, tasks[idx.get(k)][0]);

            while (k < n && tasks[idx.get(k)][0] <= time) {
                heap.offer(new int[]{tasks[idx.get(k)][1], idx.get(k)});
                k++;
            }

            int[] t = heap.poll();
            time += t[0];
            res[i] = t[1];
        }

        return res;
    }
}
