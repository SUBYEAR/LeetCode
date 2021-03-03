package com.leetcode.medium.review;

import java.util.Arrays;

/**
 * 给定编号从 0 到 n-1 的 n 个节点和一个无向边列表（每条边都是一对节点），请编写一个函数来计算无向图中连通分量的数目。
 */
public class LeetCode323 {
    public int countComponents(int n, int[][] edges) {
        int[] parents = new int[n];
        Arrays.fill(parents, -1);

        for (int[] e : edges) {
            int root1 = find(parents, e[0]);
            int root2 = find(parents, e[1]);
            if (root1 != root2) {
                parents[root1] = root2;
                n--;
            }
        }
        return n;
    }

    private int find(int[] parents, int x) {
        int root = x;
        while (parents[root] != -1) root = parents[root];
        return root;
    }

}

/**
 * public int countComponents(int n, int[][] edges) {
 *     int count = 0;
 *     List<List<Integer>> adjList = new ArrayList<>();
 *     boolean[] visited = new boolean[n];
 *
 *     for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
 *     for (int[] edge : edges) {
 *         adjList.get(edge[0]).add(edge[1]);
 *         adjList.get(edge[1]).add(edge[0]);
 *     }
 *
 *     for (int i = 0; i < n; i++) {
 *         if (!visited[i]) {
 *             count++;
 *             dfs(visited, i, adjList);
 *         }
 *     }
 *     return count;
 * }
 *
 * private void dfs(boolean[] visited, int index, List<List<Integer>> adjList) {
 *     visited[index] = true;
 *     for (int i : adjList.get(index)) {
 *         if (!visited[i]) {
 *             dfs(visited, i, adjList);
 *         }
 *     }
 * }
 *
 * public int countComponents(int n, int[][] edges) {
 *     int count = 0;
 *     List<List<Integer>> adjList = new ArrayList<>();
 *     boolean[] visited = new boolean[n];
 *
 *     for (int i = 0; i < n; i++) adjList.add(new ArrayList<>());
 *     for (int[] edge : edges) {
 *         adjList.get(edge[0]).add(edge[1]);
 *         adjList.get(edge[1]).add(edge[0]);
 *     }
 *
 *     for (int i = 0;  i < n; i++) {
 *         if (!visited[i]) {
 *             count++;
 *             Queue<Integer> queue = new LinkedList<>();
 *             queue.offer(i);
 *             while (!queue.isEmpty()) {
 *                 int index = queue.poll();
 *                 visited[index] = true;
 *                 for (int next : adjList.get(index)) {
 *                     if (!visited[next]) queue.offer(next);
 *                 }
 *             }
 *         }
 *     }
 *     return count;
 * }
 *
 */