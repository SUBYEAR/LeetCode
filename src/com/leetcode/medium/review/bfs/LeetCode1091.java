package com.leetcode.medium.review.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 *
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 *
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1091 {
    private static int[][] directions = {{0,1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
    private int row, col;
    public int shortestPathBinaryMatrix(int[][] grid) {
        row = grid.length;
        col = grid[0].length;
        if (grid[0][0] == 1 || grid[row - 1][col - 1] == 1) return -1;
        Queue<int[]> pos = new LinkedList<>();
        grid[0][0] = 1; // 直接用grid[i][j]记录从起点到这个点的最短路径长。按照题意 起点也有长度1
        pos.add(new int[]{0, 0});
        while (!pos.isEmpty() && grid[row - 1][col - 1] == 0) { // 求最短路径 使用BFS
            int[] xy = pos.remove();
            int preLength = grid[xy[0]][xy[1]]; // 当前点的路径长度
            for (int i = 0; i < 8; i++) {
                int newX = xy[0] + directions[i][0];
                int newY = xy[1] + directions[i][1];
                if (inGrid(newX, newY) && grid[newX][newY] == 0) {
                    pos.add(new int[]{newX, newY});
                    grid[newX][newY] = preLength + 1; // 下一个点的路径长度要+1
                }
            }
        }
        return grid[row - 1][col - 1] == 0 ? -1 : grid[row - 1][col - 1]; // 如果最后终点的值还是0，说明没有到达
    }

    private boolean inGrid(int x, int y){
        return x >= 0 && x < row && y >= 0 && y < col;
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