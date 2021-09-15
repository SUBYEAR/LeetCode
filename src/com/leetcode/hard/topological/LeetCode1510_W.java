package com.leetcode.hard.topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个奇怪的打印机，它有如下两个特殊的打印规则：
 *
 * 每一次操作时，打印机会用同一种颜色打印一个矩形的形状，每次打印会覆盖矩形对应格子里原本的颜色。
 * 一旦矩形根据上面的规则使用了一种颜色，那么 相同的颜色不能再被使用 。
 * 给你一个初始没有颜色的 m x n 的矩形 targetGrid ，其中 targetGrid[row][col] 是位置 (row, col) 的颜色。
 *
 * 如果你能按照上述规则打印出矩形 targetGrid ，请你返回 true ，否则返回 false 。
 * 提示：
 *
 * m == targetGrid.length
 * n == targetGrid[i].length
 * 1 <= m, n <= 60
 * 1 <= targetGrid[row][col] <= 60
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/strange-printer-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1510_W {
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;

        // 总共有60种颜色, 确定每种颜色的上下左右边界
        int[] top = new int[61];
        Arrays.fill(top, Integer.MAX_VALUE);
        int[] bottom = new int[61];
        Arrays.fill(bottom, Integer.MIN_VALUE);
        int[] left = new int[61];
        Arrays.fill(left, Integer.MAX_VALUE);
        int[] right = new int[61];
        Arrays.fill(right, Integer.MIN_VALUE);
        //遍历矩阵，获取每种颜色的上下左右边界
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j];
                top[color] = Math.min(top[color], i);
                bottom[color] = Math.max(bottom[color], i);
                left[color] = Math.min(left[color], j);
                right[color] = Math.max(right[color], j);
            }
        }

        boolean[][] visit = new boolean[61][61];
        List<Integer>[] edgeFrom = new ArrayList[61]; // 表示从i出发的有向边
        for (int i = 0; i < 61; i++) {
            edgeFrom[i] = new ArrayList<>();
        }

        int[] inDegree = new int[61]; // 表示每种颜色的入度数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int k = targetGrid[i][j];
                for (int color = 1; color <= 60; color++) {
                    //若t[i][j]位于颜色为color的矩形内部，颜色却不为color为k
                    //说明先染成color，再染成k
                    //建立有向边color → k
                    if (top[color] <= i && i <= bottom[color] && left[color] <= j && j <= right[color]) {
                        if (color != k && !visit[color][k]) {
                            edgeFrom[color].add(k);
                            inDegree[k]++;
                            visit[color][k] = true;
                        }
                    }
                }
            }
        }

        List<Integer> v = new ArrayList<>();
        int i = 1;
        while (true) {
            for (i = 1; i <= 60; i++) {
                if (inDegree[i] == 0) {
                    v.add(i);
                    inDegree[i] -= 1;
                    for (int a : edgeFrom[i]) {
                        inDegree[a]--;
                    }
                    break;
                }
            }
            if (i == 61) {
                break;
            }
        }

        return v.size() == 60;
    }
    // 链接：https://leetcode-cn.com/problems/strange-printer-ii/solution/cyi-chong-qi-guai-de-si-lu-jian-tu-tuo-bu-pai-xu-b/
}
