package com.leetcode.medium.review.dp;

import java.util.Arrays;

/**
 * 国际象棋中的骑士可以按下图所示进行移动：从某个位置跳到曼哈顿距离为3的位置上均是可以的
 * 将 “骑士” 放在电话拨号盘的任意数字键（如上图所示）上，接下来，骑士将会跳 N-1 步。每一步必须是从一个数字键跳到另一个数字键。
 *
 * 每当它落在一个键上（包括骑士的初始位置），都会拨出键所对应的数字，总共按下 N 位数字。
 *
 * 你能用这种方式拨出多少个不同的号码？
 *
 * 因为答案可能很大，所以输出答案模 10^9 + 7。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-dialer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode935_W {
    // 我们用 f(start, n) 表示骑士从数字 start 开始，跳了 n - 1 步得到不同的 n 位数字的个数
    public int knightDialer(int N) {
        int MOD = 1000_000_007;
        int[][] moves = new int[][] {
                {4, 6}, {6, 8},{7, 9}, {4, 8},
                {3, 9, 0}, {},{1, 7, 0}, {2, 6},
                {1, 3}, {2, 4}
        };
        // 使用滚动数组减少空间复杂度，这是因为 f(start, n) 只和 f(x, n - 1) 有关
        // 我们使用 dp[0][start] 和 dp[1][start] 交替表示当前和上一个状态的 f 值
        int[][] dp  = new int[2][10];
        Arrays.fill(dp[0], 1);
        for (int hops = 0; hops < N - 1; ++hops) {
            Arrays.fill(dp[~hops & 1], 0);
            for (int node = 0; node < 10; ++node)
                for (int nei: moves[node]) {
                    dp[~hops & 1][nei] += dp[hops & 1][node];
                    dp[~hops & 1][nei] %= MOD;
                }
        }

        long ans = 0;
        for (int x: dp[~N & 1])
            ans += x;
        return (int) (ans % MOD);
    }
}
