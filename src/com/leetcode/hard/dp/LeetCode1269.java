package com.leetcode.hard.dp;

/**
 * 有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
 * <p>
 * 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。
 * <p>
 * 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。
 * <p>
 * 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1269 {
    final int MODULO = 1000000007;

    public int numWays(int steps, int arrLen) {
        int maxCol = Math.min(arrLen - 1, steps);
        int[][] dp = new int[steps + 1][maxCol + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= steps; i++) {
            for (int j = 0; j <= maxCol; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MODULO;
                }
                if (j < maxCol) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % MODULO;
                }
            }
        }
        return dp[steps][0];
    }

    //index表示走到的位置
    private int dfs(int steps, int arrLen, int index) {
        //如果步数走完了，就不能往下走了，直接返回，如果最后一步停留在左下角的位置，也就是
        //index=0，说明找到了一个完整的路径回到原点，返回1，否则表示回不到原点，返回0；
        if (steps == 0) {
            return index == 0 ? 1 : 0;
        }
        long res = 0;
        //计算往3个方向走的结果
        //往左走，注意在最左边的时候是不能往左走的
        if (index > 0)
            res += dfs(steps - 1, arrLen, index - 1) % MODULO;
        //往右走，注意在最右边的时候是不能往右走的
        if (index < arrLen - 1)
            res += dfs(steps - 1, arrLen, index + 1) % MODULO;
        //往下走（也就是停留在原地）
        res += dfs(steps - 1, arrLen, index) % MODULO;
        return (int) (res % MODULO);
    }

//    作者：sdwwld
//    链接：https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/solution/shu-ju-jie-gou-he-suan-fa-di-gui-ge-dong-glyo/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
