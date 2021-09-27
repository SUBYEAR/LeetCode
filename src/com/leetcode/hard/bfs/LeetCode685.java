package com.leetcode.hard.bfs;

/**
 * 在本问题中，有根树指满足以下条件的 有向 图。该树只有一个根节点，所有其他节点都是该根节点的后继。
 * 该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点。
 *
 * 输入一个有向图，该图由一个有着 n 个节点（节点值不重复，从 1 到 n）的树及一条附加的有向边构成。
 * 附加的边包含在 1 到 n 中的两个不同顶点间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组 edges 。 每个元素是一对 [ui, vi]，用以表示有向图中连接顶点 ui 和顶点 vi 的边，其中 ui 是 vi 的一个父节点。
 *
 * 返回一条能删除的边，使得剩下的图是有 n 个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/redundant-connection-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode685 {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        // 附加的边指向根节点，则包括根节点在内的每个节点都有一个父节点，此时图中一定有环路；
        // 附加的边指向非根节点，则恰好有一个节点（即被附加的边指向的节点）有两个父节点，此时图中可能有环路也可能没有环路。
        int nodesCount = edges.length;
        UnionFind uf = new UnionFind(nodesCount + 1);
        int[] parent = new int[nodesCount + 1];
        for (int i = 1; i <= nodesCount; ++i) {
            parent[i] = i;
        }
        int conflict = -1;
        int cycle = -1;
        for (int i = 0; i < nodesCount; ++i) {
            int[] edge = edges[i];
            int node1 = edge[0], node2 = edge[1];
            // 同一条边不可能同时被记为导致冲突的边和导致环路出现的边
            if (parent[node2] != node2) { // v 有两个父节点，将当前的边 [u,v] 记为导致冲突的边
                conflict = i;
            } else {
                parent[node2] = node1;
                if (uf.find(node1) == uf.find(node2)) { // 在并查集中分别找到 u 和 v 的祖先
                    cycle = i; // 如果祖先相同，说明这条边导致环路出现
                } else {
                    uf.union(node1, node2);
                }
            }
        }
        // 只有一条附加的边，因此最多有一条导致冲突的边和一条导致环路出现的边。
        // 遍历图中的所有边之后，根据是否存在导致冲突的边和导致环路出现的边，得到附加的边
        if (conflict < 0) { // 如果没有导致冲突的边，说明附加的边一定导致环路出现
            int[] redundant = {edges[cycle][0], edges[cycle][1]};
            return redundant;
        } else {
            int[] conflictEdge = edges[conflict];
            if (cycle >= 0) {
                // 如果有导致环路的边，则附加的边不可能是 [u,v]（因为[u,v] 已经被记为导致冲突的边，
                // 不可能被记为导致环路出现的边），因此附加的边是 [parent[v],v]。
                int[] redundant = {parent[conflictEdge[1]], conflictEdge[1]};
                return redundant;
            } else {
                int[] redundant = {conflictEdge[0], conflictEdge[1]};
                return redundant;
            }
        }
    }

    private class UnionFind {
        int[] ancestor;

        public UnionFind(int n) {
            ancestor = new int[n];
            for (int i = 0; i < n; ++i) {
                ancestor[i] = i;
            }
        }

        public void union(int index1, int index2) {
            ancestor[find(index1)] = find(index2);
        }

        public int find(int index) {
            if (ancestor[index] != index) {
                ancestor[index] = find(ancestor[index]);
            }
            return ancestor[index];
        }
    }
}
