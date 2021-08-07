package com.leetcode.hard.dfs;

/**
 * 有一个 m x n 的二元网格，其中 1 表示砖块，0 表示空白。砖块 稳定（不会掉落）的前提是：
 *
 * 一块砖直接连接到网格的顶部，或者
 * 至少有一块相邻（4 个方向之一）砖块 稳定 不会掉落时
 * 给你一个数组 hits ，这是需要依次消除砖块的位置。每当消除 hits[i] = (rowi, coli) 位置上的砖块时，对应位置的砖块（若存在）会消失，
 * 然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。
 *
 * 返回一个数组 result ，其中 result[i] 表示第 i 次消除操作对应掉落的砖块数目。
 *
 * 注意，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bricks-falling-when-hit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode803 {
    // 官解法使用到了Union Find
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int h = grid.length, w = grid[0].length;

        UnionFind uf = new UnionFind(h * w + 1);
        int[][] status = new int[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                status[i][j] = grid[i][j];
            }
        }
        for (int i = 0; i < hits.length; i++) {
            status[hits[i][0]][hits[i][1]] = 0;
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (status[i][j] == 1) {
                    if (i == 0) {
                        uf.merge(h * w, i * w + j);
                    }
                    if (i > 0 && status[i - 1][j] == 1) {
                        uf.merge(i * w + j, (i - 1) * w + j);
                    }
                    if (j > 0 && status[i][j - 1] == 1) {
                        uf.merge(i * w + j, i * w + j - 1);
                    }
                }
            }
        }
        int[][] directions = {{0, 1},{1, 0},{0, -1},{-1, 0}};
        int[] ret = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            if (grid[r][c] == 0) {
                continue;
            }
            int prev = uf.size(h * w);

            if (r == 0) {
                uf.merge(c, h * w);
            }
            for (int[] direction : directions) {
                int dr = direction[0], dc = direction[1];
                int nr = r + dr, nc = c + dc;

                if (nr >= 0 && nr < h && nc >= 0 && nc < w) {
                    if (status[nr][nc] == 1) {
                        uf.merge(r * w + c, nr * w + nc);
                    }
                }
            }
            int size = uf.size(h * w);
            ret[i] = Math.max(0, size - prev - 1);
            status[r][c] = 1;
        }
        return ret;
    }
}

class UnionFind {
    int[] f;
    int[] sz;

    public UnionFind(int n) {
        f = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) {
            f[i] = i;
            sz[i] = 1;
        }
    }

    public int find(int x) {
        if (f[x] == x) {
            return x;
        }
        int newf = find(f[x]);
        f[x] = newf;
        return f[x];
    }

    public void merge(int x, int y) {
        int fx = find(x), fy = find(y);
        if (fx == fy) {
            return;
        }
        f[fx] = fy;
        sz[fy] += sz[fx];
    }

    public int size(int x) {
        return sz[find(x)];
    }


    public int[] hitBricks_DFS(int[][] grid, int[][] hits) {
        int m = grid.length;
        int n = grid[0].length;
        int length = hits.length;
        for (int i = 0; i < length; i++) {
            grid[hits[i][0]][hits[i][1]] -= 1;
        }
        // 标记稳定砖块
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
        }
        int[] res = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            int x = hits[i][0], y = hits[i][1];
            grid[x][y] += 1; // 此时砖块是2 稳定的, 1 击落的, 0空白
            if (!isStabled(grid, x, y) || grid[x][y] == 0) {
                res[i] = 0;
                continue;
            }
            // 否则 dfs 计算联通图大小，这里的联通指的是值为 1的砖块个数。
            // 实际指的是添加了 (x,y) 砖块之后，这些值为 1 的砖块会变成稳定砖块（我们用 2 表示）
            // 由于我们是反推，因此就是移除 (x, y) 砖块之后， 这些稳定的砖块会变成不稳定（掉落）
            res[i] = dfs(grid, x, y) - 1;
        }
        return res;
    }

    int dfs(int[][] grid, int x, int y) {
        if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
            grid[x][y] = 2;
            int ans = 1 + dfs(grid, x - 1, y) + dfs(grid, x + 1, y) + dfs(grid, x, y - 1)
                    + dfs(grid, x, y + 1);
            return ans;
        }
        return 0;
    }

    boolean isStabled(int[][] grid, int x, int y) {
        int m = grid.length;
        int n = grid[0].length;
        if (x == 0) {
            return true;
        }
        if (x + 1 < m && grid[x + 1][y] == 2) {
            return true;
        }
        if (x - 1 >= 0 && grid[x - 1][y] == 2) {
            return true;
        }
        if (y + 1 < n && grid[x][y + 1] == 2) {
            return true;
        }
        if (y - 1 >= 0 && grid[x][y - 1] == 2) {
            return true;
        }
        return false;
    }
}
