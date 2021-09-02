package com.leetcode.hard.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 你还记得那条风靡全球的贪吃蛇吗？
 *
 * 我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）
 * 开始移动。我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。
 *
 * 每次移动，蛇可以这样走：
 *
 * 如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
 * 如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
 * 如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
 * 如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
 * 返回蛇抵达目的地所需的最少移动次数。
 *
 * 如果无法到达目的地，请返回 -1。
 * 2 <= n <= 100
 * 0 <= grid[i][j] <= 1
 * 蛇保证从空单元格开始出发。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-reach-target-with-rotations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1210 {
    private static final int HORIZON = 0;
    private static final int VERTICAL = 1;
    int n;

    public int minimumMoves(int[][] grid) {
        int n = grid.length; // n的范围是[2,100]
        this.n = n;
        Queue<int[]> que = new ArrayDeque<>();
        boolean[][][] seen = new boolean[n][n][2];
        que.add(new int[]{HORIZON, 0, 1});
        seen[0][1][HORIZON] = true;
        int step = 0;

        while (!que.isEmpty()) {
            int size = que.size();
            while (size-- > 0) {
                int[] cur = que.poll();
                if (cur[1] == n - 1 && cur[2] == n - 1 && cur[0] == HORIZON) {
                    return step;
                }
                List<int[]> nextPosition = getNextPosition(grid, cur);
                for (int[] next : nextPosition) {
                    int dir = next[0];
                    int nextX = next[1], nextY = next[2];
                    if (getDistance(nextX, nextY) <= getDistance(cur[1], cur[2]) && !seen[nextX][nextY][dir]) {
                        que.add(next);
                        seen[nextX][nextY][dir] = true;
                    }
                }
            }
            step++;
        }
        return -1;
    }

    List<int[]> getNextPosition(int[][] grid, int[] pos) {
        List<int[]> ans = new ArrayList<>();
        int dir = pos[0];
        int headX = pos[1], headY = pos[2];
        if (dir == HORIZON && headY + 1 < n && grid[headX][headY + 1] == 0) {
            ans.add(new int[]{HORIZON, headX, headY + 1});
        }
        if (dir == VERTICAL && headX + 1 < n && grid[headX + 1][headY] == 0) {
            ans.add(new int[]{VERTICAL, headX + 1, headY});
        }
        if (dir == HORIZON && headX + 1 < n && headY - 1 >= 0 &&
                grid[headX + 1][headY] == 0 && grid[headX + 1][headY - 1] == 0) {
            ans.add(new int[]{HORIZON, headX + 1, headY});
            // 处于水平状态并且其下面的两个单元都是空的就顺时针旋转 90 度
            ans.add(new int[]{VERTICAL, headX + 1, headY - 1});
        }
        if (dir == VERTICAL && headY + 1 < n && headX - 1 >= 0 &&
                grid[headX][headY + 1] == 0 && grid[headX - 1][headY + 1] == 0) {
            ans.add(new int[]{VERTICAL, headX, headY + 1});

            // 竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度
            ans.add(new int[]{HORIZON, headX - 1, headY + 1});
        }
        return ans;
    }

    int getDistance(int x, int y) {
        return n - x + n - y - 2;
    }
}
