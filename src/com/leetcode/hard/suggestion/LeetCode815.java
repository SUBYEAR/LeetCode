package com.leetcode.hard.suggestion;

import java.util.*;

/**
 * 给你一个数组 routes ，表示一系列公交线路，其中每个 routes[i] 表示一条公交线路，第 i 辆公交车将会在上面循环行驶。
 *
 * 例如，路线 routes[0] = [1, 5, 7] 表示第 0 辆公交车会一直按序列 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... 这样的车站路线行驶。
 * 现在从 source 车站出发（初始时不在公交车上），要前往 target 车站。 期间仅可乘坐公交车。
 *
 * 求出 最少乘坐的公交车数量 。如果不可能到达终点车站，返回 -1 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * 输出：2
 * 解释：最优策略是先乘坐第一辆公交车到达车站 7 , 然后换乘第二辆公交车到车站 6 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bus-routes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode815 {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int startRow = 0, endRow = 0;
        int m = routes.length, n = routes[0].length;

        for (int row = 0; row < m; row++) {
            for (int i = 0; i < n; i++) {
                if (routes[row][i] == source) {
                    startRow = row;
                }
                if (routes[row][i] == target) {
                    endRow = row;
                }
                buildEdges(graph, i == 0 ? routes[row][n - 1] : routes[row][i - 1], routes[row][i]);
            }
        }
        if (startRow == endRow) {
            return 0;
        }
        Set<Integer> visit = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        for (int sta : routes[startRow]) {
            q.offer(sta);
            visit.add(sta);
        }
        int res = 1;
        while (!q.isEmpty()) {
            res++;
            while (q.size() > 0) {
                int cur = q.poll();
                System.out.println("当前车站是：" + cur);

                if (cur == target) {
                    return res;
                }

                for (int next : graph.get(cur)) {
                    if (visit.contains(next)) {
                        continue;
                    }
                    visit.add(next);
                    q.offer(next);
                }
            }

        }
        return -1;
    }

    private void buildEdges(Map<Integer, Set<Integer>> graph, int from, int to) {
        Set<Integer> list1 = graph.getOrDefault(from, new HashSet<>());
        list1.add(to);
        graph.put(from, list1);

        Set<Integer> list2 = graph.getOrDefault(to, new HashSet<>());
        list2.add(from);
        graph.put(to, list2);
    }
}
