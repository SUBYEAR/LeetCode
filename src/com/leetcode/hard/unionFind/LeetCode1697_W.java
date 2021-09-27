package com.leetcode.hard.unionFind;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 给你一个 n 个点组成的无向图边集 edgeList ，其中 edgeList[i] = [ui, vi, disi] 表示
 * 点 ui 和点 vi 之间有一条长度为 disi 的边。请注意，两个点之间可能有 超过一条边 。
 *
 * 给你一个查询数组queries ，其中 queries[j] = [pj, qj, limitj] ，你的任务是对于每个查询 queries[j] ，
 * 判断是否存在从 pj 到 qj 的路径，且这条路径上的每一条边都 严格小于 limitj 。
 *
 * 请你返回一个 布尔数组 answer ，其中 answer.length == queries.length ，当 queries[j] 的查询结
 * 果为 true 时， answer 第 j 个值为 true ，否则为 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/checking-existence-of-edge-length-limited-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1697_W {
    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        int len = queries.length;

        // 将queries按照limit从小到大顺序进行排序 离散化
        List<Integer> idx = IntStream.rangeClosed(0, len - 1).boxed()
                                     .sorted(Comparator.comparingInt(o -> queries[o][2]))
                                     .collect(Collectors.toList());
        // 将edgeList从小到大排序
        Arrays.sort(edgeList, Comparator.comparingInt(o -> o[2]));

        // 用指针i表示当前往并查集中添加的最后一条边处理询问queries[j]时由于limit是递增的，因此只需要不断往并查集中添加新的边
        // 直到当前指向的边dis>=limit, 随后我们只需要用并查集判断p和q是否连接
        int i = 0;
        UF uf = new UF(n);
        boolean[] res = new boolean[len];
        for (int index : idx) {
            while (i < edgeList.length && edgeList[i][2] < queries[index][2]) {
                uf.unite(edgeList[i][0], edgeList[i][1]);
                ++i;
            }
            res[index] = uf.isConnect(queries[index][0], queries[index][1]);
        }
        return res;
    }

    class UF {
        int n;
        int[] parent;
        int[] size;
        public UF(int n) {
            this.n = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 两种路径压缩的查找方式
        private int find1(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        private int find2(int x) { // 这种压缩方式最终所有的子节点都挂在一个父节点上
            if (x != parent[x]) {
                parent[x] = find2(x);
            }
            return parent[x]; // 注意这里返回的是parent[x]
        }

        public int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }

        public boolean isConnect(int x, int y) {
            return find(x) == find(y);
        }

        void unite(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return;
            }

            if (size[x] < size[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            parent[y] = x;
            size[x] += size[y];
            return;
        }
    }
}
