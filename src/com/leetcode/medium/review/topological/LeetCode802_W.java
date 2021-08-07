package com.leetcode.medium.review.topological;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
public class LeetCode802_W {
    // 官解
    // 若起始节点位于一个环内，或者能到达一个环，则该节点不是安全的。否则，该节点是安全的。
    //我们可以使用深度优先搜索来找环，并在深度优先搜索时，用三种颜色对节点进行标记，标记的规则如下：
    //白色（用 0 表示）：该节点尚未被访问；
    //灰色（用 1 表示）：该节点位于递归栈中，或者在某个环上；
    //黑色（用 2 表示）：该节点搜索完毕，是一个安全节点。
    // 首次访问一个节点时，将其标记为灰色，并继续搜索与其相连的节点。
    //如果在搜索过程中遇到了一个灰色节点，则说明找到了一个环，此时退出搜索，栈中的节点仍保持为灰色，这一做法可以将「找到了环」这一信息传递到栈中的所有节点上。
    //如果搜索过程中没有遇到灰色节点，则说明没有遇到环，那么递归返回前，我们将其标记由灰色改为黑色，即表示它是一个安全的节点。

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (safe(graph, color, i)) {
                ans.add(i);
            }
        }
        return ans;
    }

    public boolean safe(int[][] graph, int[] color, int x) {
        if (color[x] > 0) {
            return color[x] == 2;
        }
        color[x] = 1;
        for (int y : graph[x]) {
            if (!safe(graph, color, y)) {
                return false;
            }
        }
        color[x] = 2;
        return true;
    }

    // 拓扑排序 若一个节点没有出边，则该节点是安全的；若一个节点出边相连的点都是安全的，则该节点也是安全的。
    // 我们可以将图中所有边反向，得到一个反图，然后在反图上运行拓扑排序
    // 首先得到反图 rg 及其入度数组 inDeg。将所有入度为 0 的点加入队列，然后不断取出队首元素，将其出边相连的点的入度减一，
    // 若该点入度减一后为 0，则将该点加入队列，如此循环直至队列为空。循环结束后，所有入度为 0 的节点均为安全的。
    // 我们遍历入度数组，并将入度为 0 的点加入答案列表。
    public List<Integer> eventualSafeNodes_ts(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> rg = new ArrayList<List<Integer>>();
        for (int i = 0; i < n; ++i) {
            rg.add(new ArrayList<Integer>());
        }
        int[] inDeg = new int[n];
        for (int x = 0; x < n; ++x) {
            for (int y : graph[x]) {
                rg.get(y).add(x);
            }
            inDeg[x] = graph[x].length;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int y = queue.poll();
            for (int x : rg.get(y)) {
                if (--inDeg[x] == 0) {
                    queue.offer(x);
                }
            }
        }

        List<Integer> ans = new ArrayList<Integer>();
        for (int i = 0; i < n; ++i) {
            if (inDeg[i] == 0) {
                ans.add(i);
            }
        }
        return ans;
    }

    Map<Integer, Boolean> map = new HashMap<>();
    public List<Integer> eventualSafeNodes_self(int[][] graph) {
        int n = graph.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(i);
        }

        int[] edgeTo = new int[n];
        Arrays.fill(edgeTo, -1);
        for (int i = 0; i < n; i++) {
            Arrays.fill(edgeTo, -1);
            dfs(graph, i, new boolean[n], new boolean[n], edgeTo);
            dfs(graph, n - i - 1, new boolean[n], new boolean[n], edgeTo);
        }
        set.removeAll(map.keySet());
        ArrayList<Integer> res = new ArrayList<>(set);
        return res;
    }

    public void dfs(int[][] graph, int v, boolean[] visit, boolean[] onStack, int[] edgeTo) {
        if (map.getOrDefault(v, false)) {
            return;
        }

        onStack[v] = true;
        visit[v] = true;
        for (int w : graph[v]) {
            if (map.containsKey(w)) {
                for (int x = v; x != -1; x = edgeTo[x]) {
                    map.put(x, Boolean.TRUE);
//                    System.out.print(x + ", ");
                }
                return;
            } else if (!visit[w]) {
                edgeTo[w] = v;
                dfs(graph, w, visit, onStack, edgeTo);
            } else if (onStack[w]) { // 如果w被访问过，要么是被其他路径访问的，要么是在这次遍历访问的
                for (int x = v; x != w; x = edgeTo[x]) {
                    map.put(x, Boolean.TRUE);
//                    System.out.print(x + ", ");
                }
                map.put(w, Boolean.TRUE);
//                System.out.println(w);
            }
        }
        onStack[v] = false;
    }
}
