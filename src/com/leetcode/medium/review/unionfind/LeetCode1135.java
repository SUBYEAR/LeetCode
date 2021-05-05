package com.leetcode.medium.review.unionfind;

import java.util.Arrays;

/**
 * 想象一下你是个城市基建规划者，地图上有 N 座城市，它们按以 1 到 N 的次序编号。
 *
 * 给你一些可连接的选项 conections，其中每个选项 conections[i] = [city1, city2, cost] 
 * 表示将城市 city1 和城市 city2 连接所要的成本。（连接是双向的，也就是说城市 city1 和城市 city2 
 * 相连也同样意味着城市 city2 和城市 city1 相连）。
 *
 * 返回使得每对城市间都存在将它们连接在一起的连通路径（可能长度为 1 的）最小成本。
 * 该最小成本应该是所用全部连接代价的综合。如果根据已知条件无法完成该项任务，则请你返回 -1。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/connecting-cities-with-minimum-cost
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * Kruskal算法 = 贪心 + 并查集
 *
 * 流程：将所有边按cost从小到大排序，然后使用并查集依次尝试合并每个边：
 *
 * 如果合并成功，则加入这条边。
 * 如果合并失败（边的两个节点已经是合并过的），说明产生了环，则丢弃这条边。
 * 通过并查集合并后，每个连通分量节点都会有相同的root，因此检查所有节点的root：
 *
 * 如果检查到只有一个root，说明这个图只有一个连通分量，是连通图，返回cost。
 * 如果检查到超过一个root，说明这个图有多个连通分量，不是一个连通图，返回-1。
 */
public class LeetCode1135 {
    public int minimumCost(int N, int[][] connections) {
        // sort connections by cost from small to large
        Arrays.sort(connections, (a, b) -> a[2]-b[2]);

        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
        }

        int cost = 0;
        for (int[] edge : connections) {
            if (union(edge[0], edge[1], parent)) {
                cost += edge[2];
            }
        }

        // System.out.println(Arrays.toString(parent));

        int p = -1;
        for (int i = 1; i <= N; ++i) {
            int root = findRoot(i, parent);
            if (p == -1) {
                p = root;
            } else if (p != root) {
                return -1;
            }
        }
        return cost;
    }

    public int findRoot(int x, int[] parent) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public boolean union(int a, int b, int[] parent) {
        a = findRoot(a, parent);
        b = findRoot(b, parent);
        if (a == b) return false;
        parent[a] = b;
        return true;
    }
}
