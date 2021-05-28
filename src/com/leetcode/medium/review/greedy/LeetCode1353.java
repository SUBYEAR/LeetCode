/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。注意，一天只能参加一个会议。
 * 请你返回你可以参加的 最大 会议数目。
 * 解题思路：
 * 贪心的思想，对于第 i 天，如果有若干的会议都可以在这一天开，那么我们肯定是让 endDay 小的会议先在这一天开才会使答案最优，因为
 * endDay 大的会议可选择的空间是比 endDay小的多的，所以在满足条件的会议需要让 endDay 小的先开。
 *
 * @since 2020-06-11
 */
public class LeetCode1353 {
    public int maxEvents(int[][] events) {
        // 首先排序：开始时间小的在前。这样是方便我们顺序遍历，把开始时间一样的都放进堆
        Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
        // 小顶堆记录所有当前可参加会议的结束时间
        // 在每一个时间点，我们首先将当前时间点开始的会议加入小根堆
        // 再把当前已经结束的会议移除出小根堆（因为已经无法参加了）
        // 然后从剩下的会议中选择一个结束时间最早的去参加
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        int day = 0, id = 0, n = events.length, res = 0;
        while (id < n || !queue.isEmpty()) {
            if (queue.isEmpty()) {
                queue.add(events[id][1]);
                day = events[id++][0];
            }
            while (id < n && events[id][0] <= day) {
                queue.add(events[id++][1]);
            }
            // 从1开始遍历所有时间,对于每一个时间点，所有在 当前时间及之前时间开始，并且在当前时间还未结束的会议都是可参加的
            if (queue.peek() >= day) {
                day++; // 遍历所有时间点
                res++;
            }
            queue.poll();
        }
        return res;
    }
}

//        //首先排序：开始时间小的在前。这样是方便我们顺序遍历，把开始时间一样的都放进堆
//         Arrays.sort(events, (o1, o2) -> o1[0] - o2[0]);
//         //小顶堆
//         PriorityQueue<Integer> pq = new PriorityQueue<>();
//         //结果、开始时间、events下标、有多少组数据
//         int res = 0, last = 1, i = 0, n = events.length;
//         while (i < n || !pq.isEmpty()) {
//             //将start相同的会议都放进堆里
//             while (i < n && events[i][0] == last) {
//                 pq.offer(events[i++][1]);
//             }
//             //pop掉当前天数之前的
//             while (!pq.isEmpty() && pq.peek() < last) {
//                 pq.poll();
//             }
//             //顶上的就是俺们要参加的
//             if (!pq.isEmpty()) {
//                 pq.poll();
//                 res++;
//             }
//             last++;
//         }
//         return res;
//

// 利用数据范围改进
//         PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
//         Arrays.sort(events, new Comparator<int[]>(){
//             @Override
//             public int compare(int[] a, int[] b){
//                 return a[0] - b[0];
//             }
//         });
//         int i = 0, res = 0, n = events.length;
//         for (int d = 1; d <= 100000; ++d) {
//             while (i < n && events[i][0] == d){
//                 queue.offer(events[i++][1]);
//             }
//             while (queue.size() > 0 && queue.peek() < d){
//                 queue.poll();
//             }
//             if (queue.size() > 0) {
//                 queue.poll();
//                 res++;
//             }
//         }
//         return res;
//
