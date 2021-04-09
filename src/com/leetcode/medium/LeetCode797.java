package com.leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *给一个有 n 个结点的有向无环图，找到所有从 0 到 n-1 的路径并输出（不要求按顺序）
 * 二维数组的第 i 个数组中的单元都表示有向图中 i 号结点所能到达的下一些结点（译者注：
 * 有向图是有方向的，即规定了 a→b 你就不能从 b→a ）空就是没有下一个结点了。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-paths-from-source-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode797 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ArrayList<Integer> path = new ArrayList<>();
        path.add(0);
        backtrack(graph, 0, path);
        return res;
    }

    void backtrack(int[][] graph, int start, ArrayList<Integer> path) {
        if (start == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < graph[start].length; i++) {
            path.add(graph[start][i]);
            backtrack(graph, graph[start][i], path);
            path.remove(path.size() - 1);
        }
    }
}
