package com.leetcode.medium.review;

import java.util.Arrays;

/**
 * 给定从 0 到 n-1 标号的 n 个结点，和一个无向边列表（每条边以结点对来表示），请编写一个函数用来判断这些边是否能够形成一个合法有效的树结构。
 */
public class LeetCode261 {
    //并查集
    public boolean validTree1(int n, int[][] edges) {
        DisjointSet disjointSet = new DisjointSet(n);
        for (int[] edge : edges) {
            if (!disjointSet.union(edge[0], edge[1]))
                return false;
        }
        return disjointSet.getCount() == 1;
    }


    //并查集
    class DisjointSet {
        int n;
        int[] parent;
        int[] rank;
        int count; //连同分量

        DisjointSet(int n) {
            this.n = n;
            this.parent = new int[n];
            Arrays.fill(parent, -1);
            this.rank = new int[n];
            this.count = n;
        }

        //寻找父节点
        private int findRoot(int x) {
            int root = x;
            while (parent[root] != -1) {
                root = parent[root];
            }
            return root;
        }

        public boolean union(int x, int y) {
            int xRoot = findRoot(x);
            int yRoot = findRoot(y);
            //如果两个在同一颗树上，直接返回false
            if (xRoot == yRoot) return false;
            //压缩路径
            if (rank[xRoot] > rank[yRoot]) {
                parent[yRoot] = xRoot;
            } else if (rank[xRoot] < rank[yRoot]) {
                parent[xRoot] = yRoot;
            } else if (rank[xRoot] == rank[yRoot]) {
                parent[xRoot] = yRoot;
                rank[yRoot]++;
            }
            count--;
            return true;
        }

        public int getCount() {
            return this.count;
        }
    }
}

/**
 * //BFS
 *     public boolean validTree(int n, int[][] edges) {
 *         //构建邻接矩阵
 *         int[][] graph = new int[n][n];
 *         //有边的元素设置为1，没有边的元素设置为0
 *         for (int[] edge : edges) {
 *             graph[edge[0]][edge[1]] = 1;
 *             graph[edge[1]][edge[0]] = 1;
 *         }
 *         //进行BFS
 *         Queue<Integer> queue = new LinkedList<>();
 *         //从第一个节点开始搜索，这样就不会漏掉无边图的情况
 *         queue.add(0);
 *         boolean[] visited = new boolean[n];
 *         while (!queue.isEmpty()) {
 *             Integer cur = queue.poll();
 *             visited[cur] = true;
 *             //获取邻接点
 *             for (int i = 0; i < n; i++) {
 *                 //查看当前节点的邻接点
 *                 if (graph[cur][i] == 1) {
 *                     //如果访问过，则返回false
 *                     if (visited[i])
 *                         return false;
 *
 *                     //标记邻接点，入队列
 *                     visited[i] = true;
 *                     //涂黑访问过的节点
 *                     graph[cur][i] = 0;
 *                     graph[i][cur] = 0;
 *                     queue.add(i);
 *                 }
 *             }
 *         }
 *
 *         //判断是否为单连通分量
 *         for (int i = 0; i < n; i++) {
 *             if (!visited[i])
 *                 return false;
 *         }
 *         return true;
 *     }
 *
 * //DFS
 *     public boolean validTree(int n, int[][] edges) {
 *         //构建邻接矩阵
 *         int[][] graph = new int[n][n];
 *         //有边的元素设置为1，没有边的元素设置为0
 *         for (int[] edge : edges) {
 *             graph[edge[0]][edge[1]] = 1;
 *             graph[edge[1]][edge[0]] = 1;
 *         }
 *
 *         //进行DFS
 *         Stack<Integer> stack = new Stack<>();
 *         stack.add(0);
 *         boolean[] visited = new boolean[n];
 *         while(!stack.isEmpty()){
 *             Integer cur = stack.pop();
 *             visited[cur] = true;
 *             for(int i = 0;i < n;i++){
 *                 if(graph[cur][i] == 1){
 *                     if(visited[i]) return false;
 *
 *                     visited[i] = true;
 *                     graph[cur][i] = 0;
 *                     graph[i][cur] = 0;
 *                     stack.add(i);
 *                 }
 *             }
 *         }
 *
 *         //判断是否为单连通分量
 *         for (int i = 0; i < n; i++) {
 *             if (!visited[i])
 *                 return false;
 *         }
 *         return true;
 *     }
 *
 */