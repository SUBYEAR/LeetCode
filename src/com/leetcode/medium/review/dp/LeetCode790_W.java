package com.leetcode.medium.review.dp;

/**
 * 有两种形状的瓷砖：一种是 2x1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
 * XX  <- 多米诺
 *
 * XX  <- "L" 托米诺
 * X
 * 给定 N 的值，有多少种方法可以平铺 2 x N 的面板？返回值 mod 10^9 + 7。
 *
 * （平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/domino-and-tromino-tiling
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode790_W {
    public int numTilings(int n) {
        // dp[state]表示当前列在不同状态下铺砖方式的数量. state的第i位是1表示第i行铺砖
        // 每一列有4中不同的铺砖状态,每种状态可以在前一列和当前列已存在不同铺砖的状态下转换得到.*表示未铺的面板，X表示瓷砖
        // **  X*  X*  XX  XX
        // **  X*  XX  X*  XX (0 --> 0, 1, 2, 3)

        // **  XX  XX
        // Y*  Y*  YX (1 --> 2, 3)

        // Y*  Y*  YX
        // **  XY  XX (2 --> 1, 3)

        // Y*  Y*
        // Y*  Y* (3 --> 0)

        int MOD = 1_000_000_007;
        long[] dp = new long[] {1, 0, 0, 0};
        for (int i = 0; i < n; i++) {
            long[] ndp = new long[4];
            ndp[0b00] = (dp[0b00] + dp[0b11]) % MOD;
            ndp[0b01] = (dp[0b00] + dp[0b10]) % MOD;
            ndp[0b10] = (dp[0b00] + dp[0b01]) % MOD;
            ndp[0b11] = (dp[0b00] + dp[0b01] + dp[0b10]) % MOD;
            dp = ndp;
        }
        return (int) dp[0]; // 为什么不返回dp[3]而是dp[0]呢？ 当遍历结束的时候，dp[0]才是铺好的状态，dp[3]已经超出N的限制了
    }

    // 公式推导法 dp[i] 表示 i列的平铺次数， dp2[i] 表示i列缺1的平铺次数，即平铺了2(i - 1)面板后还多一块出来的状态
    // dp2[i] = dp[i-1] + dp2[i-1] // 后一项表示(i-1)多一块时再加上水平铺一块XX
    // dp[i] = dp[i-1] + dp[i-2] + 2 * dp2[i-2]
    // 由以上公式可推导dp[i]= 2dp[i-1] + dp[i-3]
    //     private int mod = 1000000007;
    //    public int numTilings(int N) {
    //        int[] dp = new int[N+3];
    //        dp[0] = 1;
    //        dp[1] = 1;
    //        dp[2] = 2;
    //        dp[3] = 5;
    //        for(int i = 4; i <= N; i++){
    //            dp[i] = (2*(dp[i-1] % mod) % mod + dp[i-3] % mod) % mod;
    //        }
    //        return dp[N];
    //    }
    //
    //链接：https://leetcode-cn.com/problems/domino-and-tromino-tiling/solution/zong-jie-yi-xia-da-lao-de-dpsi-lu-by-huan-shi-cai-/
}
