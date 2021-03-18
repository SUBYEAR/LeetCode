package com.leetcode.hard;

import java.util.Arrays;

/**
Alice 和 Bob 用几堆石子在做游戏。几堆石子排成一行，每堆石子都对应一个得分，由数组 stoneValue 给出。
Alice 和 Bob 轮流取石子，Alice 总是先开始。在每个玩家的回合中，该玩家可以拿走剩下石子中的的前 1、2 或 3 堆石子 。
比赛一直持续到所有石头都被拿走。
每个玩家的最终得分为他所拿到的每堆石子的对应得分之和。每个玩家的初始分数都是 0 。比赛的目标是决出最高分，
 得分最高的选手将会赢得比赛，比赛也可能会出现平局。
假设 Alice 和 Bob 都采取 最优策略 。如果 Alice 赢了就返回 "Alice" ，Bob 赢了就返回 "Bob"，平局（分数相同）返回 "Tie" 。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/stone-game-iii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1406 {
    public String stoneGameIII(int[] stoneValue) {
        int length = stoneValue.length;
        int[] dp = new int[length + 1];   // dp表示玩家之间的最大分差
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[length] = 0;


        for (int i = length - 1; i >= 0; i--) {
            int pre = 0;
            for (int j = i + 1; j <= i + 3 && j <= length; ++j) {
                pre += stoneValue[j - 1];
                dp[i] = Math.max(dp[i], pre - dp[j]);
            }

        }

        return dp[0] == 0 ? "Tie" : dp[0] > 0 ? "Alice" : "Bob";
    }

    //    public String stoneGameIII(int[] stoneValue) {
    //        int n = stoneValue.length;
    //        int[] suffixSum = new int[n];
    //        suffixSum[n - 1] = stoneValue[n - 1];
    //        for (int i = n - 2; i >= 0; --i) {
    //            suffixSum[i] = suffixSum[i + 1] + stoneValue[i];
    //        }
    //        int[] f = new int[n + 1]; //  f[i] 表示还剩下stoneValue[i...]堆石子时当前玩家最多从这些石子中拿的数目
    //        // 边界情况，当没有石子时，分数为 0
    //        // 为了代码的可读性，显式声明
    //        f[n] = 0;
    //        for (int i = n - 1; i >= 0; --i) {
    //            int bestj = f[i + 1];
    //            for (int j = i + 2; j <= i + 3 && j <= n; ++j) {
    //                bestj = Math.min(bestj, f[j]);
    //            }
    //            f[i] = suffixSum[i] - bestj;
    //        }
    //        int total = 0;
    //        for (int value : stoneValue) {
    //            total += value;
    //        }
    //        if (f[0] * 2 == total) {
    //            return "Tie";
    //        } else {
    //            return f[0] * 2 > total ? "Alice" : "Bob";
    //        }
    //    }
}
