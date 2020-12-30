/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

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
        // 小顶堆
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
            if (queue.peek() >= day) {
                day++;
                res++;
            }
            queue.poll();
        }
        return res;
    }
}
