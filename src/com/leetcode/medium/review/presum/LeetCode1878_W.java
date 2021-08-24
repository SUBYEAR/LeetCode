package com.leetcode.medium.review.presum;

import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 给你一个 m x n 的整数矩阵 grid 。
 *
 * 菱形和 指的是 grid 中一个正菱形 边界 上的元素之和。本题中的菱形必须为正方形旋转45度，且四个角都在一个格子当中。
 * 下图是四个可行的菱形，每个菱形和应该包含的格子都用了相应颜色标注在图中。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/get-biggest-three-rhombus-sums-in-a-grid
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1878_W {
    int N = 110; // 最大长度是100
    int[][] sum1= new int[N][N];
    int[][] sum2= new int[N][N];

    public int[] getBiggestThree(int[][] grid) {
        // 记 sum1[x][y] 表示从位置 (x-1, y-1) 开始往左上方走，走到边界为止的所有格子的元素和。
        // sum1[x][y] = sum1[x - 1][y - 1] + grid[x - 1][y - 1]
        // 记 sum2[x][y] 表示从位置 (x-1, y-1) 开始往右上方走，走到边界为止的所有格子的元素和。
        // sum2[x][y] = sum1[x - 1][y + 1] + grid[x - 1][y - 1]
        // 菱形可以由两个点和菱形的长度度量,菱形边上的和由四条边减去四个顶点(ux,uy) (dx,dy) (lx,ly) (rx,ry)的和组成
        // sum2[lx+1][ly+1]-sum2[ux][uy+2] 要注意sum数组比点要领先1为所以是lx+1,
        // 然后sum和是左闭右闭区间类比sum[right] - sum[left-1],所以减去的是(ux+1,uy+1)更前面的一个点(ux, uy+2)
        // sum1[rx+1][ry+1] - sum1[ux][uy]
        // sum1[dx+1][dy+1] - sum1[lx][ly]
        // sum2[dx+1][dy+1] - sum2[rx][ry+2]
        // grid[ux][uy]+grid[dx][dy]+grid[lx][ly]+grid[rx][ry]
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum1[i][j] = sum1[i - 1][j - 1] + grid[i - 1][j - 1];
                sum2[i][j] = sum2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                set.add(grid[i][j]); // 单独的一个格子也是菱形
                for (int k = i + 2; k < m; k += 2) {
                    int ux = i, uy = j;
                    int dx = k, dy = j;
                    int lx = (i + k) / 2, ly = j - (k - i) / 2;
                    int rx = (i + k) / 2, ry = j + (k - i) / 2;
                    if (ly < 0 || ry >= n) {
                        break;
                    }
                    int tmp = sum2[lx + 1][ly + 1] - sum2[ux][uy + 2] +
                              sum1[rx + 1][ry + 1] - sum1[ux][uy]  +
                              sum1[dx + 1][dy +1 ] - sum1[lx][ly] +
                              sum2[dx + 1][dy + 1] - sum2[rx][ry + 2] -
                              (grid[ux][uy] + grid[dx][dy] + grid[lx][ly] + grid[rx][ry]);
                    set.add(tmp);
                }
                while(set.size() > 3){
                    set.pollFirst();
                }
            }
        }
        if(set.size() == 1){
            return new int[]{set.pollFirst()};
        }
        if(set.size() == 2){
            return new int[]{set.pollLast(),set.pollLast()};
        }
        return new int[]{set.pollLast(),set.pollLast(),set.pollLast()};
    }
}
