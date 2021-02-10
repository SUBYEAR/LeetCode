package com.leetcode.medium;

/**
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 *
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 *
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-with-minimum-effort
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1631 {
    int res = 0;
    int count = 0;

    public int minimumEffortPath(int[][] heights) {
        boolean [][] visit = new boolean[heights.length][heights[0].length];
        travel(heights, visit, 0, 0, 0);
        return 0;
    }

    private void travel(int[][] heights, boolean[][] visit, int row, int col, int diff) {
        printIndent(count++);
        if (row < 0 || row >= heights.length || col < 0 || col >= heights[0].length) {
            printIndent(--count);
            System.out.println("Out of Boundary");
            return;
        }
        if (visit[row][col]) {
            printIndent(--count);
            System.out.println("Visited");
            return;
        }
        System.out.println("row = " + row + ", col = " + col);
        visit[row][col] = true;
        travel(heights, visit, row - 1, col, diff);
        travel(heights, visit, row + 1, col, diff);
        travel(heights, visit, row, col - 1, diff);
        travel(heights, visit, row, col + 1, diff);
        printIndent(--count);
        System.out.println("Count: " + count + ". Over");
    }

    private void printIndent(int n) {
        for (int i = 0; i < n; i++) {
            System.out.print("    ");
        }
    }
}
