package com.leetcode.hard.unionFind;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (这个问题与 尽量减少恶意软件的传播 是一样的，不同之处用粗体表示。)
 *
 * 在节点网络中，只有当 graph[i][j] = 1 时，每个节点 i 能够直接连接到另一个节点 j。
 *
 * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，那么两个节点都
 * 将被恶意软件感染。这种恶意软件的传播将继续，直到没有更多的节点可以被这种方式感染。
 *
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 *
 * 我们可以从初始列表中删除一个节点，并完全移除该节点以及从该节点到任何其他节点的任何连接。如果移除这一节点将最小
 * 化 M(initial)， 则返回该节点。如果有多个节点满足条件，就返回索引最小的节点。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimize-malware-spread-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode928 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1 ; j < graph[0].length; j++) {
                if (graph[i][j] == 1) {
                    List<Integer> list1 = map.getOrDefault(i, new ArrayList<>());
                    list1.add(j);
                    map.put(i, list1);

                    List<Integer> list2 = map.getOrDefault(j, new ArrayList<>());
                    list2.add(i);
                    map.put(j, list2);
                }
            }
        }

        Arrays.sort(initial);
        UF origin = getUf(map, n);
        List<Integer> list = Arrays.stream(initial).boxed().collect(Collectors.toList());
        int originCnt = getConfectionCnt(origin, list);
        int diff = -1;
        int ans = 0;
        for (int val : initial) {
            Map<Integer, List<Integer>> removed = new HashMap<>(map);
            for (int idx : removed.getOrDefault(val, new ArrayList<>())) {
                List<Integer> idxList = new ArrayList<>(removed.get(idx));
                idxList.remove(Integer.valueOf(val));
                if (idxList.size() == 0) {
                    removed.remove(idx);
                } else {
                    removed.put(idx, idxList);
                }
            }
            removed.remove(val);

            List<Integer> tmp = new ArrayList<>(list);
            tmp.remove(Integer.valueOf(val));
            int cnt = getConfectionCnt(getUf(removed, n), tmp);
            if (originCnt - cnt > diff) {
                diff = originCnt - cnt;
                ans = val;
            }
        }
        return ans;
    }

    int getConfectionCnt(UF uf, List<Integer> confection) {
        int cnt = 0;
        List<Integer> roots = new ArrayList<>();
        for (int val : confection) {
            int root = uf.find(val);
            if (!roots.contains(root)) {
                cnt += uf.size[root];
                roots.add(root);
            }
        }
        return cnt;
    }

    UF getUf(Map<Integer, List<Integer>> graph, int n) {
        UF uf = new UF(n);
        for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
            int i = entry.getKey();
            for (int j : entry.getValue()) {
                uf.unite(i, j);
            }
        }
        return uf;
    }

    private static class UF {
        int[] parent;
        int n;
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

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        void unite(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return;
            }

            if (size[x] < size[y]) {
                int tmp = x;
                x = y;
                y = tmp;
            }
            parent[y] = x;
            size[x] += size[y];
        }
    }
}
