package com.leetcode.hard.dp;

/**
 * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
 *
 * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
 *
 * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。
 * 如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
 *
 * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/super-egg-drop
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode887 {
    // dp[k][n] 表示k个鸡蛋确定n个楼层的情况下最少扔鸡蛋的次数
    // res = min(res, max(dp[k][n-i] + 1, dp[k-1][i - 1])
    // 鸡蛋没碎那么k不变，要确认的是i以上的楼层即i+1~n;鸡蛋碎了k-1继续确认从1~i-1高于 f 的楼层落下的鸡蛋都会碎的f具体是什么
    public int superEggDrop(int k, int n) {
        // dp[k][m]=n 数组表示当前有k个鸡蛋，可以尝试扔m次鸡蛋，在这个状态下，最坏情况下最多测试一栋n层楼
        // base case:
        // dp[0][..] = 0
        // dp[..][0] = 0
        int[][] dp = new int[k + 1][n + 1]; // m 最多不会超过 N 次（线性扫描）
        int m = 0;
        while (dp[k][m] < n) {
            m++;
            for (int i = 1; i <= k; i++) {
                dp[i][m] = dp[i][m - 1] + dp[i - 1][m - 1] + 1;
            }
        }
        return m;
    }
}
