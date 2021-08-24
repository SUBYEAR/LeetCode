package com.leetcode.hard.dp;

import java.util.Arrays;

/**
 * 给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。道路的长度用一个长度为 n 的整数
 * 数组 dist 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
 *
 * 当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，
 * 因为那时你已经抵达会议现场。
 *
 * 例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 2 小时，
 * 就无需等待，可以直接继续。
 * 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，
 * 你可能在不同时刻到达接下来的道路。
 *
 * 例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。跳过第 1 条道路的休息时间意味着你将会在恰好 2
 * 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
 * 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-skips-to-arrive-at-meeting-on-time
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1883 {
    final double EPS = 1e-8;
    final double INFTY = 1e20;

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        // 我们用 f[i][j] 表示经过了 dist[0] 到 dist[i−1] 的 i 段道路，并且跳过了 j 次的最短用时
        int n = dist.length;
        double[][] f = new double[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], INFTY);
        }
        f[0][0] = 0;
        // 在进行状态转移时，我们考虑最后一段道路 dist[i−1] 是否选择跳过：
        for (int i= 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j != i) { // 如果没有跳过
                    f[i][j] = Math.min(f[i][j], Math.ceil(f[i - 1][j] + (double)dist[i - 1] / speed - EPS));
                }
                if (j != 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + (double) dist[i - 1] / speed);
                }
            }
        }

        for (int j = 0; j <= n; ++j) {
            if (f[n][j] < hoursBefore + EPS) {
                return j;
            }
        }
        return -1;
    }

    // 将数组 dist 中的道路长度和 hoursBefore 都乘以 speed。
    // 由于方法一的代码中所有除法运算的除数都是 speed，因此这样做可以保证所有的除法运算的结果都是整数，从根本上避免浮点数误差
    // 时间 x 的下一个 speed 的倍数小时为 ([(x-1)/speed] + 1)*speed 其中[]表示向下取整即计算机中常用的除法
    public int minSkips_(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        long[][] f = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(f[i], Integer.MAX_VALUE);
        }
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if (j != i) { // 当 j=i 时，我们不能通过「没有跳过」进行转移
                    f[i][j] = Math.min(f[i][j], ((f[i - 1][j] + dist[i - 1] - 1) / speed + 1) * speed);
                }
                if (j != 0) { // 当 j=0 时，我们不能通过「跳过」进行转移
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + dist[i - 1]);
                }
            }
        }

        for (int j = 0; j <= n; ++j) {
            if (f[n][j] <= (long) hoursBefore * speed) {
                return j;
            }
        }
        return -1;
    }

}
