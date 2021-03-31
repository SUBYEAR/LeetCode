package com.leetcode.hard.suggestion;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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
    class Solution {
        public int numBusesToDestination(int[][] routes, int source, int target) { // 超时解法
            if (source == target) {
                return 0;
            }

            int m = routes.length;
            ArrayList<Integer>[] graph = new ArrayList[m];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
            Set<Integer> start = new HashSet<>(), end = new HashSet<>();
            for (int row = 0; row < m; row++) {
                Set<Integer> routeSet = Arrays.stream(routes[row]).boxed().collect(Collectors.toSet());
                if (routeSet.contains(source)) {
                    start.add(row);
                }
                if (routeSet.contains(target)) {
                    end.add(row);
                }

                for (int j = row + 1; j < m; j++) {
                    Set<Integer> routeSet1 = Arrays.stream(routes[row]).boxed().collect(Collectors.toSet());
                    Set<Integer> routeSet2 = Arrays.stream(routes[j]).boxed().collect(Collectors.toSet());
                    routeSet1.retainAll(routeSet2);
                    if (!routeSet1.isEmpty()) {
                        graph[row].add(j);
                        graph[j].add(row);
                    }
                }
            }

            int res = Integer.MAX_VALUE;
            for (int startRow : start) {
                int level = 0;
                boolean[] visit = new boolean[m];
                Queue<Integer> q = new LinkedList<>();
                q.offer(startRow);
                visit[startRow] = true;

                while (!q.isEmpty()) {
                    int size = q.size();
                    level++;
                    while (size-- > 0) {
                        int cur = q.poll();
                        if (end.contains(cur)) {
                            res = Math.min(res, level);
                        }

                        for (int next : graph[cur]) {
                            if (visit[next]) {
                                continue;
                            }
                            visit[next] = true;
                            q.offer(next);
                        }
                    }
                }
            }
            return res == Integer.MAX_VALUE ? -1 : res;
        }
    }

    public int numBusesToDestination(int[][] routes, int S, int T) { // 官方解法
        if (S == T) {
            return 0;
        }
        int N = routes.length;

        List<List<Integer>> graph = new ArrayList();
        for (int i = 0; i < N; ++i) {
            Arrays.sort(routes[i]);
            graph.add(new ArrayList());
        }
        Set<Integer> seen = new HashSet();
        Set<Integer> targets = new HashSet();
        Queue<Point> queue = new ArrayDeque();

        // Build the graph.  Two buses are connected if
        // they share at least one bus stop.
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                if (intersect(routes[i], routes[j])) {
                    graph.get(i).add(j);
                    graph.get(j).add(i);
                }
            }
        }

        // Initialize seen, queue, targets.
        // seen represents whether a node has ever been enqueued to queue.
        // queue handles our breadth first search.
        // targets is the set of goal states we have.
        for (int i = 0; i < N; ++i) {
            if (Arrays.binarySearch(routes[i], S) >= 0) {
                seen.add(i);
                queue.offer(new Point(i, 0));
            }
            if (Arrays.binarySearch(routes[i], T) >= 0)
                targets.add(i);
        }

        while (!queue.isEmpty()) {
            Point info = queue.poll();
            int node = info.x, depth = info.y;
            if (targets.contains(node)) {
                return depth + 1;
            }
            for (Integer nei : graph.get(node)) {
                if (!seen.contains(nei)) {
                    seen.add(nei);
                    queue.offer(new Point(nei, depth + 1));
                }
            }
        }

        return -1;
    }

    public boolean intersect(int[] A, int[] B) {
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) return true;
            if (A[i] < B[j]) i++; else j++;
        }
        return false;
    }
}
