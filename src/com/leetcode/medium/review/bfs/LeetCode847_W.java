package com.leetcode.medium.review.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 *
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 *
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 *
 * 提示：
 *
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] 不包含 i
 * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * 输入的图总是连通图
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-path-visiting-all-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode847_W {
    // 使用三元组 (u,mask,dist) 表示队列中的每一个元素，其中：
    // u 表示当前位于的节点编号；
    // mask 是一个长度为 n 的二进制数，表示每一个节点是否经过。如果 mask 的第 i 位是 1，则表示节点 i 已经过，否则表示节点 i 未经过；
    // dist 表示到当前节点为止经过的路径长度。
    // 初始时，我们将所有的 (i, 2^i, 0) 放入队列，表示可以从任一节点开始。在搜索的过程中，如果当前三元组中的 mask 包含 n 个 1，那么就可以返回
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean [n][1 << n]; // 这个seen数组很妙，结合了n的范围很难想到
        for (int i = 0; i < n; i++) {
            queue.offer(new int[] {i, 1 << i, 0});
            seen[i][1 << i] = true;
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int u = cur[0], mask = cur[1], dist = cur[2];
            if (mask == (1 << n - 1)) {
                return dist;
            }
            for (int adj : graph[u]) {
                int maskV = mask | 1 << adj;
                if (!seen[adj][maskV]) {
                    queue.offer(new int[] {adj, maskV, dist + 1});
                    seen[adj][maskV] = true;
                }
            }
        }
        return 0;
    }
}
