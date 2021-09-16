package com.leetcode.hard.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 给你一个 m * n 的网格，其中每个单元格不是 0（空）就是 1（障碍物）。每一步，您都可以在空白单元格中上、下、左、右移动。
 *
 * 如果您 最多 可以消除 k 个障碍物，请找出从左上角 (0, 0) 到右下角 (m-1, n-1) 的最短路径，并返回通过该路径所需的步数。如果找不到这样的路径，则返回 -1。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-path-in-a-grid-with-obstacles-elimination
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1293_W {
    int[] dx = new int[] {1,-1,0,0};
    int[] dy = new int[] {0,0,1,-1};
    public int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][][] visit = new boolean[m][n][k + 1];
        Queue<int[]> que = new ArrayDeque<>();
        visit[0][0][0] = true;
        que.add(new int[] {0,0,0});
        int step = 0;

        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                int[] poll = que.poll();
                int x = poll[0], y = poll[1], z = poll[2];

                if (poll[0] == m - 1 && poll[1] == n - 1) {
                    return step;
                }
                for (int i = 0; i < dx.length; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (!isSafe(grid, nx, ny, z, visit)) {
                        continue;
                    }
                    int layer = getLayer(grid, nx, ny, z, k);
                    if (layer >= 0) {
                        boolean seen = false;
                        for (int l = 0; l < layer; l++) { // 这里的循环用来剪枝解决超时问题
                            // 如果k很大的话不同的平面上遍历会重复遍历其他平面上已经遍历了的点x,y
                            if (visit[nx][ny][l]) {
                               seen = true;
                            }
                        }
                        if (!seen) {
                            visit[nx][ny][layer] = true;
                            que.add(new int[]{nx, ny, layer});
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isSafe(int[][] grid, int x, int y, int z, boolean[][][] visit) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || visit[x][y][z]) {
            return false;
        }
        return true;
    }

    private int getLayer(int[][] grid, int x, int y, int z, int layer) {
        if (grid[x][y] == 1) {
            return z < layer ? z + 1 : -1;
        }
        return z;
    }
}
