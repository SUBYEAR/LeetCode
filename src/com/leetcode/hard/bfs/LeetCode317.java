package com.leetcode.hard.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 你是个房地产开发商，想要选择一片空地 建一栋大楼。你想把这栋大楼够造在一个距离周边设施都比较方便的地方，通过调研，
 * 你希望从它出发能在 最短的距离和 内抵达周边全部的建筑物。请你计算出这个最佳的选址到周边全部建筑物的 最短距离和。
 *
 *  
 *
 * 提示：
 *
 * 你只能通过向上、下、左、右四个方向上移动。
 *
 * 给你一个由 0、1 和 2 组成的二维网格，其中：
 *
 * 0 代表你可以自由通过和选择建造的空地
 * 1 代表你无法通行的建筑物
 * 2 代表你无法通行的障碍物
 *  
 *
 * 示例：
 *
 * 输入：[[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
 *
 * 1 - 0 - 2 - 0 - 1
 * |   |   |   |   |
 * 0 - 0 - 0 - 0 - 0
 * |   |   |   |   |
 * 0 - 0 - 1 - 0 - 0
 * 输出：7
 * 解析：
 * 给定三个建筑物 (0,0)、(0,4) 和 (2,2) 以及一个位于 (0,2) 的障碍物。
 * 由于总距离之和 3+3+1=7 最优，所以位置 (1,2) 是符合要求的最优地点，故返回7。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shortest-distance-from-all-buildings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode317 {


        private int[][] dirs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        // 思路：
        //  (1) 从每一个建筑物开始进行广度优先搜索
        //  (2) 在搜索的同时计算每一个空格到这个建筑物的距离
        //  (3) 在搜索的同时将每一个空格到每一个建筑物的距离进行累加，得到每个空格到所有建筑物的距离
        //  (4) 取空格到所有建筑物的最小距离
        public int shortestDistance(int[][] grid) {
            int m = grid.length;
            if (m == 0) {
                return 0;
            }
            int n = grid[0].length;

            int[][] totalDist = new int[m][n];
            int res = Integer.MAX_VALUE;
            // 用于标记空地
            int mark = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // (1) 从每一个建筑物开始进行广度优先搜索
                    if (grid[i][j] == 1) {
                        res = bfs(grid, m, n, i, j, mark, totalDist);
                        // 每次遍历搜索完一个建筑物，这个标记减一，表示所有空地被遍历一次了
                        mark--;
                    }
                }
            }

            return res;
        }

        private int bfs(int[][] grid, int m, int n, int i, int j, int mark, int[][] totalDist) {
            int res = Integer.MAX_VALUE;
            Queue<int[]> queue = new LinkedList<>();
            // 队列中每个数组有 3 个元素，分别表示：
            // 第一个元素和第二个元素表示坐标值
            // 第三个元素表示当前坐标到建筑物的距离
            // 第三个元素的初始值为 0 的原因是：一开始的时候从当前建筑物到当前建筑物的距离是 0
            queue.add(new int[]{i, j, 0});
            while (!queue.isEmpty()) {
                int[] curr = queue.poll();
                int currDist = curr[2];
                for (int[] dir : dirs) {
                    int row = curr[0] + dir[0];
                    int col = curr[1] + dir[1];
                    if (row >= 0 && row < m && col >= 0 && col < n && grid[row][col] == mark) {
                        // (2) 在搜索的同时计算每一个空格到这个建筑物的距离
                        int dist = currDist + 1;
                        // (3) 在搜索的同时将每一个空格到每一个建筑物的距离进行累加
                        totalDist[row][col] += dist;
                        // (4) 取空格到所有建筑物的最小距离
                        res = Math.min(res, totalDist[row][col]);

                        queue.add(new int[]{row, col, dist});
                        // 和 mark 标识对应
                        grid[row][col]--;
                    }
                }
            }
            return res == Integer.MAX_VALUE ? -1 : res;
        }

}
