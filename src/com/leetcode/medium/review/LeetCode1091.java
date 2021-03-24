package com.leetcode.medium.review;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode1091 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int res = 0;
        int n = grid.length;
        int[] dx = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
        boolean[][] visit = new boolean[n][n];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {0, 0});
        while (!que.isEmpty()) {
            int size = que.size();
            res++;
            while (size-- > 0) {
                int[] cur = que.poll();
                if (visit[cur[0]][cur[1]]||grid[cur[0]][cur[1]] == 1) {
                    continue;
                }

                visit[cur[0]][cur[1]] = true;
                if ((cur[0] == n - 1) && (cur[1] == n - 1)) {
                    return res;
                }
                for (int i = 0; i < dx.length; i++) {
                    int nextX = cur[0] + dx[i];
                    int nextY = cur[1] + dy[i];
                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0) {
                        que.offer(new int[]{nextX, nextY});
                    }
                }
            }
        }

        return -1;
    }
}

// 另一种解法
//         int res = 0;
//        int n = grid.length;
//        int[] dx = new int[] {-1, 0, 1, -1, 1, -1, 0, 1};
//        int[] dy = new int[] {-1, -1, -1, 0, 0, 1, 1, 1};
//        Queue<int[]> que = new LinkedList<>();
//        que.add(new int[] {0, 0});
//        while (!que.isEmpty()) {
//            int size = que.size();
//            res++;
//            while (size-- > 0) {
//                int[] cur = que.poll();
//                if (grid[cur[0]][cur[1]] == 1) {
//                    continue;
//                }
//
//                if ((cur[0] == n - 1) && (cur[1] == n - 1)) {
//                    return res;
//                }
//                grid[cur[0]][cur[1]] = 1;
//                for (int i = 0; i < dx.length; i++) {
//                    int nextX = cur[0] + dx[i];
//                    int nextY = cur[1] + dy[i];
//                    if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0) {
//                        que.offer(new int[]{nextX, nextY});
//                    }
//                }
//            }
//        }
//
//        return -1;