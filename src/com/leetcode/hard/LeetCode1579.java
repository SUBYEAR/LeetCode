package com.leetcode.hard;

/**
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
 *
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [typei, ui, vi] 表示节点 ui 和 vi 之间存在类型为 typei 的双向边。
 * 请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob
 * 都可以到达所有其他节点，则认为图是可以完全遍历的。
 *
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1579 {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Union u1 = new Union(n);
        Union u2 = new Union(n);
        int res = 0;
        // 节点编号改为从 0 开始
        for (int[] edge : edges) {
            --edge[1];
            --edge[2];
        }

        // 公共边
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (u1.connected(edge[1], edge[2])) { // Alice 中已经有这条边了那么可以将这条边变成Bob的独占边
                    ++res;
                } else {
                    u2.union(edge[1], edge[2]);
                    u1.union(edge[1], edge[2]);
                }
            }
        }

        // 独占边
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                // Alice 独占边
                if (u1.connected(edge[1], edge[2])) {
                    ++res;
                }
                u1.union(edge[1], edge[2]);
            } else if (edge[0] == 2) {
                // Bob 独占边
                if (u2.connected(edge[1], edge[2])) {
                    ++res;
                }
                u2.union(edge[1], edge[2]);
            }
        }

        if (u1.count != 1 || u2.count != 1) {
            return -1;
        }
        return res;

    }

    private class Union {
        private int[] parent;
        private int count;
        int[] size;

        public Union(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n ; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) {
                return;
            }

            if (size[rootP] < size[rootQ]) {
                int temp = rootP;
                rootP = rootQ;
                rootQ = temp;
            }

            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
            --count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        private int find(int p) {
            while (p != parent[p]) {
                p = parent[p];
            }
            return p;
        }
    }
}


//class Solution {
//    public int maxNumEdgesToRemove(int n, int[][] edges) {
//        UnionFind ufa = new UnionFind(n);
//        UnionFind ufb = new UnionFind(n);
//        int ans = 0;
//
//        // 节点编号改为从 0 开始
//        for (int[] edge : edges) {
//            --edge[1];
//            --edge[2];
//        }
//
//        // 公共边
//        for (int[] edge : edges) {
//            if (edge[0] == 3) {
//                if (!ufa.unite(edge[1], edge[2])) {
//                    ++ans;
//                } else {
//                    ufb.unite(edge[1], edge[2]);
//                }
//            }
//        }
//
//        // 独占边
//        for (int[] edge : edges) {
//            if (edge[0] == 1) {
//                // Alice 独占边
//                if (!ufa.unite(edge[1], edge[2])) {
//                    ++ans;
//                }
//            } else if (edge[0] == 2) {
//                // Bob 独占边
//                if (!ufb.unite(edge[1], edge[2])) {
//                    ++ans;
//                }
//            }
//        }
//
//        if (ufa.setCount != 1 || ufb.setCount != 1) {
//            return -1;
//        }
//        return ans;
//    }
//}
//
//// 并查集模板
//class UnionFind {
//    int[] parent;
//    int[] size;
//    int n;
//    // 当前连通分量数目
//    int setCount;
//
//    public UnionFind(int n) {
//        this.n = n;
//        this.setCount = n;
//        this.parent = new int[n];
//        this.size = new int[n];
//        Arrays.fill(size, 1);
//        for (int i = 0; i < n; ++i) {
//            parent[i] = i;
//        }
//    }
//
//    public int findset(int x) {
//        return parent[x] == x ? x : (parent[x] = findset(parent[x]));
//    }
//
//    public boolean unite(int x, int y) {
//        x = findset(x);
//        y = findset(y);
//        if (x == y) {
//            return false;
//        }
//        if (size[x] < size[y]) {
//            int temp = x;
//            x = y;
//            y = temp;
//        }
//        parent[y] = x;
//        size[x] += size[y];
//        --setCount;
//        return true;
//    }
//
//    public boolean connected(int x, int y) {
//        x = findset(x);
//        y = findset(y);
//        return x == y;
//    }
//}
//
//作者：LeetCode-Solution
//链接：https://leetcode-cn.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/solution/bao-zheng-tu-ke-wan-quan-bian-li-by-leet-mtrw/
//来源：力扣（LeetCode）
//著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。