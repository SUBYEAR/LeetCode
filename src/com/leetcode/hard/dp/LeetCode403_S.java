package com.leetcode.hard.dp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。
 * 青蛙可以跳上石子，但是不可以跳入水中。给你石子的位置列表 stones（用单元格序号 升序 表示）， 
 * 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。
 *  另请注意，青蛙只能向前方（终点的方向）跳跃。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frog-jump
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode403_S {
    Boolean[][] record; // 青蛙位于第 i 个石子，上次跳跃距离为 lastDis
    public boolean canCross(int[] stones) {
        int n = stones.length;
        record = new Boolean[n][n];
        return dfs(stones, 0, 0);
    }

    boolean dfs(int[] stones, int i, int lastDis) {
        if (i == stones.length - 1) {
            return true;
        }
        if (record[i][lastDis] != null) {
            return record[i][lastDis];
        }

        for (int curDis = lastDis - 1; curDis <= lastDis + 1; curDis++) {
            int j = Arrays.binarySearch(stones, i + 1, stones.length, stones[i] + curDis);
            if (j > 0 && dfs(stones, j, curDis)) {
                return record[i][lastDis] = true;
            }
        }

        return record[i][lastDis] = false;
    }

    public boolean canCross_DP(int[] stones) {
        int n = stones.length;
        boolean[][] dp = new boolean[n][n];
        dp[0][0] = true;
        for (int i = 1; i < n; ++i) {
            if (stones[i] - stones[i - 1] > i) { // 当第 i 个石子与第 i-1 个石子距离超过 i 时，青蛙必定无法到达终点。
                return false;
            }
        }
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                int k = stones[i] - stones[j];
                if (k > j + 1) { // 「现在所处的石子编号」为 i 时，「上一次跳跃距离」k 必定满足 k≤i。
                    break;
                }
                dp[i][k] = dp[j][k - 1] || dp[j][k] || dp[j][k + 1];
                if (i == n - 1 && dp[i][k]) {
                    return true;
                }
            }
        }
        return false;
    }
}
