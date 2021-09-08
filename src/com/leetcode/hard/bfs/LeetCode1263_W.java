package com.leetcode.hard.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 *
 * 游戏地图用大小为 n * m 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
 *
 * 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
 *
 * 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符 '.' 表示，意味着可以自由行走。
 * 墙用字符 '#' 表示，意味着障碍物，不能通行。 
 * 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-move-a-box-to-their-target-location
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1263_W {
    private static final int[][] directions = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    public int minPushBox(char[][] grid) {
        int startX = -1, startY = -1;
        int targetX = -1, targetY = -1;
        int peopleX = -1, peopleY = -1;
        int n = grid.length, m = grid[0].length;
        boolean[][][] visit  = new boolean[n][m][4]; // visit数组表示箱子在x,y位置时，人所在的directions数组中对应位置
        Queue<Point>  q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'T') {
                    targetX = i;
                    targetY = j;
                } else if (grid[i][j] == 'B') {
                    startX = i;
                    startY = j;
                } else if (grid[i][j] == 'S') {
                    peopleX = i;
                    peopleY = j;
                }
            }
        }

        grid[startX][startY] = '#';
        for (int i = 0; i < directions.length; i++) {
            int[] dir =  directions[i];
            int x = startX + dir[0];
            int y = startY + dir[1];
            if (canReach(grid, n, m, peopleX, peopleY, x, y)) {
                visit[startX][startY][i] = true;
                q.add(new Point(startX, startY, x, y));
            }
        }
        grid[startX][startY] = 'B';
        int step = 0;
        while (!q.isEmpty()) {
            for (int i = q.size(); i > 0; i--) {
                Point point = q.poll(); // 箱子当前的位置是(x,y),人的位置是(peopleX,peopleY)
                if (point.x == targetX && point.y == targetY) {
                    return step;
                }

                grid[point.x][point.y] = '#';
                if (point.x - 1 >= 0 && grid[point.x - 1][point.y] != '#' && !visit[point.x - 1][point.y][2]
                        && canReach(grid, n, m, point.peopleX, point.peopleY, point.x + 1, point.y)) {
                    // 如果人可以到底箱子位置x方向上的+1位置处，就可以把箱子推到减1位置
                    q.add(new Point(point.x - 1, point.y, point.x, point.y)); // 人的位置也要刷新
                    // 最后一个维度2表示是从人x+1位置上把x位置的箱子推到x-1处
                    visit[point.x - 1][point.y][2] = true;
                }
                if (point.x + 1 < n && grid[point.x + 1][point.y] != '#' && !visit[point.x + 1][point.y][3]
                        && canReach(grid, n, m, point.peopleX, point.peopleY, point.x - 1, point.y)) {
                    q.add(new Point(point.x + 1, point.y, point.x, point.y));
                    visit[point.x + 1][point.y][3] = true;
                }
                if (point.y - 1 >= 0 && grid[point.x][point.y-1] != '#' && !visit[point.x][point.y - 1][1]
                        && canReach(grid, n, m, point.peopleX,point.peopleY, point.x, point.y + 1)) {
                    q.add(new Point(point.x, point.y - 1, point.x, point.y));
                    visit[point.x][point.y - 1][1] = true;
                }
                if (point.y + 1 < m && grid[point.x][point.y + 1] != '#' && !visit[point.x][point.y + 1][0]
                        && canReach(grid, n, m, point.peopleX, point.peopleY, point.x, point.y - 1)) {
                    q.add(new Point(point.x, point.y + 1, point.x, point.y));
                    visit[point.x][point.y + 1][0] = true;
                }
                grid[point.x][point.y] = '.';
            }
            step++;
        }
        return -1;
    }

    private boolean canReach(char[][] grid, int n, int m, int startX, int startY, int targetX, int targetY) {
        if (targetX < 0 || targetX >= n || targetY < 0 || targetY >= m) {
            return false;
        }

        Stack<Point> s = new Stack<>();
        boolean[][] visit = new boolean[n][m];
        s.push(new Point(startX, startY));
        visit[startX][startY] = true;
        while (!s.isEmpty()) {
            Point cur = s.pop();
            if (cur.x == targetX && cur.y == targetY) {
                return true;
            }

            for (int[] dir : directions) {
                int x = cur.x + dir[0];
                int y = cur.y + dir[1];
                if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != '#' && !visit[x][y]) {
                    visit[x][y] = true;
                    s.push(new Point(x, y));
                }
            }
        }
        return false;
    }

    private static class Point {
        int x;
        int y;
        int peopleX;
        int peopleY;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int peopleX, int peopleY) {
            this.x = x;
            this.y = y;
            this.peopleX = peopleX;
            this.peopleY = peopleY;
        }
    }
}
