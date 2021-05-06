package com.leetcode.medium.review.bfs;

import java.util.Arrays;

/**
 * 由空地和墙组成的迷宫中有一个球。球可以向上下左右四个方向滚动，但在遇到墙壁前不会停止滚动。当球停下时，可以选择下一个方向。
 * 给定球的起始位置，目的地和迷宫，找出让球停在目的地的最短距离。距离的定义是球从起始位置（不包括）到目的地
 * （包括）经过的空地个数。如果球无法停在目的地，返回 -1。
 * 迷宫由一个0和1的二维数组表示。 1表示墙壁，0表示空地。你可以假定迷宫的边缘都是墙壁。起始位置和目的地的坐标通过行号和列号给出。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/the-maze-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode505 {
    public int shortestDistance(int[][] maze, int[] start, int[] dest) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row: distance)
            Arrays.fill(row, Integer.MAX_VALUE);
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, distance);
        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
    }

    public void dfs(int[][] maze, int[] start, int[][] distance) {
        int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};
        for (int[] dir: dirs) {
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            int count = 0;
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[x - dir[0]][y - dir[1]]) {
                distance[x - dir[0]][y - dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x - dir[0],y - dir[1]}, distance);
            }
        }
    }
}

// public int shortestDistance(int[][] maze, int[] start, int[] dest) {
//        int[][] distance = new int[maze.length][maze[0].length];
//        for (int[] row: distance)
//            Arrays.fill(row, Integer.MAX_VALUE);
//        distance[start[0]][start[1]] = 0;
//         int[][] dirs={{0, 1} ,{0, -1}, {-1, 0}, {1, 0}};
//        Queue < int[] > queue = new LinkedList < > ();
//        queue.add(start);
//        while (!queue.isEmpty()) {
//            int[] s = queue.remove();
//            for (int[] dir: dirs) {
//                int x = s[0] + dir[0];
//                int y = s[1] + dir[1];
//                int count = 0;
//                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
//                    x += dir[0];
//                    y += dir[1];
//                    count++;
//                }
//                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
//                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
//                    queue.add(new int[] {x - dir[0], y - dir[1]});
//                }
//            }
//        }
//        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
//    }
//

/* 我们可以使用 Dijkstra 算法直接求出从起始位置到终点位置的最短路。这里不会详细介绍 Dijkstra 算法的实现，只会描述如何建立这道题对应的图。

对于迷宫中的任意一个空地 0，即为 x，它可以往四个方向滚动，假设它往上下左右分别可以滚动到位置 p, q, r, s，
那么可以从 x 向 p, q, r, s 分别连一条权值为经过空地个数的边，注意这条边是单向边，因为从 x 可以滚动到位置 p 不代表从 p 一定可以滚动到位置 x。

在连完所有的边之后，我们以起始位置为源，使用 Dijkstra 算法计算出其到所有其它位置的最短路长度，也就得到了从起始位置到目的地最少经过的空地个数。
*/
// public int shortestDistance(int[][] maze, int[] start, int[] dest) {
//        int[][] distance = new int[maze.length][maze[0].length];
//        boolean[][] visited = new boolean[maze.length][maze[0].length];
//        for (int[] row: distance)
//            Arrays.fill(row, Integer.MAX_VALUE);
//        distance[start[0]][start[1]] = 0;
//        dijkstra(maze, distance, visited);
//        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
//    }
//    public int[] minDistance(int[][] distance, boolean[][] visited) {
//        int[] min={-1,-1};
//        int min_val = Integer.MAX_VALUE;
//        for (int i = 0; i < distance.length; i++) {
//            for (int j = 0; j < distance[0].length; j++) {
//                if (!visited[i][j] && distance[i][j] < min_val) {
//                    min = new int[] {i, j};
//                    min_val = distance[i][j];
//                }
//            }
//        }
//        return min;
//    }
//    public void dijkstra(int[][] maze, int[][] distance, boolean[][] visited) {
//        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
//        while (true) {
//            int[] s = minDistance(distance, visited);
//            if (s[0] < 0)
//                break;
//            visited[s[0]][s[1]] = true;
//            for (int[] dir: dirs) {
//                int x = s[0] + dir[0];
//                int y = s[1] + dir[1];
//                int count = 0;
//                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
//                    x += dir[0];
//                    y += dir[1];
//                    count++;
//                }
//                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
//                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
//                }
//            }
//        }
//    }
//

// Dijkstra 算法 + 堆优化
// public int shortestDistance(int[][] maze, int[] start, int[] dest) {
//        int[][] distance = new int[maze.length][maze[0].length];
//        for (int[] row: distance)
//            Arrays.fill(row, Integer.MAX_VALUE);
//        distance[start[0]][start[1]] = 0;
//        dijkstra(maze, start, distance);
//        return distance[dest[0]][dest[1]] == Integer.MAX_VALUE ? -1 : distance[dest[0]][dest[1]];
//    }
//
//    public void dijkstra(int[][] maze, int[] start, int[][] distance) {
//        int[][] dirs={{0,1},{0,-1},{-1,0},{1,0}};
//        PriorityQueue < int[] > queue = new PriorityQueue < > ((a, b) -> a[2] - b[2]);
//        queue.offer(new int[]{start[0],start[1],0});
//        while (!queue.isEmpty()) {
//            int[] s = queue.poll();
//            if(distance[s[0]][s[1]] < s[2])
//                continue;
//            for (int[] dir: dirs) {
//                int x = s[0] + dir[0];
//                int y = s[1] + dir[1];
//                int count = 0;
//                while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
//                    x += dir[0];
//                    y += dir[1];
//                    count++;
//                }
//                if (distance[s[0]][s[1]] + count < distance[x - dir[0]][y - dir[1]]) {
//                    distance[x - dir[0]][y - dir[1]] = distance[s[0]][s[1]] + count;
//                    queue.offer(new int[]{x - dir[0], y - dir[1], distance[x - dir[0]][y - dir[1]]});
//                }
//            }
//        }
//    }
//