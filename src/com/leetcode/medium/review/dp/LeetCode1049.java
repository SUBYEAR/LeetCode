package com.leetcode.medium.review.dp;

/**
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头最小的可能重量。如果没有石头剩下，就返回 0。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/last-stone-weight-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1049 {
    // 考虑到石头要么加要么减，所以可以 " 分成两堆 "，问两堆最小的差值。那么问题就变成了一个简单的背包），求两堆石头的最小差值。
    // sum / 2：要求差值最小，越接近 sum / 2 越则差值就越小。于是就想象成有一个背包最多能装 sum/2 的石头，
    // 看在不超过 sum / 2 的范围最多能装多少石头。
    public int lastStoneWeightII(int[] stones) { // 正难则反
        // dp[i][j]：前 i 个石头里面，重量不超过 j 的最佳组合的重量。
        int n = stones.length;
        int sum = 0;
        for(int stone : stones) {
            sum += stone;
        }

        int target = sum / 2;
        int[][] dp = new int[n][target + 1];
        for (int j = 0; j <= target; j++) {
            dp[0][j] = j >= stones[0] ? stones[0] : 0;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= target; j++) {
                if (j < stones[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - stones[i]] + stones[j]);
                }
            }
        }
        return sum - 2 * dp[n - 1][target];
    }
    //链接：https://leetcode-cn.com/problems/last-stone-weight-ii/solution/zui-hou-yi-kuai-shi-tou-de-zhong-liang-i-bl2a/

    // https://leetcode-cn.com/problems/last-stone-weight-ii/solution/yi-pian-wen-zhang-chi-tou-bei-bao-wen-ti-5lfv/
}
