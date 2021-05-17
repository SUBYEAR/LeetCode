package com.leetcode.medium;

/**
 * 有一个二维矩阵 grid ，每个位置要么是陆地（记号为 0 ）要么是水域（记号为 1 ）。
 *
 * 我们从一块陆地出发，每次可以往上下左右 4 个方向相邻区域走，能走到的所有陆地区域，我们将其称为一座「岛屿」。
 *
 * 如果一座岛屿 完全 由水域包围，即陆地边缘上下左右所有相邻区域都是水域，那么我们将其称为 「封闭岛屿」。
 *
 * 请返回封闭岛屿的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-closed-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1254 {
    public int closedIsland(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        boolean[][] visit = new  boolean[row][col];
        int boarder = 2;
        for (int i = 0; i < row; i++) {
            if (grid[i][0] == 0) {
                dfs(grid, i, 0, visit, boarder);
            }
            if (grid[i][col - 1] == 0) {
                dfs(grid, i, col - 1, visit, boarder);
            }
        }

        for (int j = 1; j < col - 1; j++) {
            if (grid[0][j] == 0) {
                dfs(grid, 0, j, visit, boarder);
            }
            if (grid[row - 1][j] == 0) {
                dfs(grid, row - 1, j, visit, boarder);
            }
        }

        int cnt = boarder;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j, visit, ++cnt);
                }
            }
        }

        return cnt - boarder;
    }

    void dfs(int[][] grid, int x, int y, boolean[][] visit, int newValue) {
        if (!isSafe(grid.length, grid[0].length, x, y)) {
            return;
        }

        if (visit[x][y]) {
            return;
        }

        if (grid[x][y] != 0) {
            return;
        }

        visit[x][y] = true;
        grid[x][y] = newValue;
        dfs(grid,x + 1, y, visit, newValue);
        dfs(grid,x - 1, y, visit, newValue);
        dfs(grid,x, y + 1, visit, newValue);
        dfs(grid,x, y - 1, visit, newValue);
    }

    boolean isSafe(int row, int col, int x, int y) {
        return !(x < 0 || x >= row || y < 0 || y >= col);
    }
}
