package com.leetcode.medium.review.dp;

/**
 * 给定一个 m × n 的网格和一个球。球的起始坐标为 (i,j) ，你可以将球移到相邻的单元格内，或者往上、下、左、右四个方向上移动使球穿过
 * 网格边界。但是，你最多可以移动 N 次。找出可以将球移出边界的路径数量。答案可能非常大，返回 结果 mod 109 + 7 的值。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/out-of-boundary-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode576 {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        if (maxMove == 0) return 0;
        int[][] dp = new int[m][n];
        int[] dr = new int[] {-1, 1, 0, 0};
        int[] dc = new int[] {0, 0, 1, -1};
        final int MOD = 100_000_0007;
        // 初始化
        for (int i = 0 ; i < m; i++) {
            dp[i][0] += 1;
            dp[i][n - 1] += 1;
        }
        for (int i = 0 ; i < n; i++) {
            dp[0][i] += 1;
            dp[m - 1][i] += 1;
        }

        for (int k = 1; k < maxMove; k++) {
            int[][] dp2 = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    for (int r = 0; r < dr.length; r++) {
                        int x = i + dr[r];
                        int y = j + dc[r];
                        if (x < 0 || x >= m || y < 0 || y >= n) {
                            dp2[i][j] = (dp2[i][j] + 1) % MOD;
                        } else {
                            dp2[i][j] = (dp2[i][j] + dp[x][y]) % MOD;
                        }
                    }
                }
            }
            dp = dp2;
        }
        return dp[startRow][startColumn];
    }

    int res = 0;
    int m;
    int n;
    void dfs(int startRow, int startColumn,  int maxMove) {
        if (maxMove < 0) {
            return;
        }

        if (isOutOfBoundary(m, n, startRow, startColumn)) {
            ++res;
            return;
        }

        dfs(startRow - 1, startColumn, maxMove - 1);
        dfs(startRow + 1, startColumn, maxMove - 1);
        dfs(startRow, startColumn - 1, maxMove - 1);
        dfs(startRow, startColumn + 1, maxMove - 1);
    }

    boolean isOutOfBoundary(int m, int n, int startRow, int startColumn) {
        return startRow < 0 || startRow >= m || startColumn < 0 || startColumn >= n;
    }
}
