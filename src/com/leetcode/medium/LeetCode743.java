package com.leetcode.medium;

import java.util.*;

/**
 * 有 n 个网络节点，标记为 1 到 n。
 *
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，
 * 其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 *
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/network-delay-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode743 {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            graph.putIfAbsent(time[0], new LinkedList<>());
            List<int[]> des = graph.get(time[0]);
            des.add(new int[] {time[1], time[2]});
        }
        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();
        q.add(k);
        cost[k - 1] = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int[] adj : graph.getOrDefault(cur, new LinkedList<>())) {
                int next = adj[0];
                if (cost[cur - 1] + adj[1] < cost[next - 1]) {
                    cost[next - 1] = cost[cur - 1] + adj[1];
                    q.add(next);
                }
            }
        }
        int maxTime = Arrays.stream(cost).max().getAsInt();
        return maxTime == Integer.MAX_VALUE ? -1 : maxTime;
    }
}
