package com.leetcode.hard.greedy;

import java.util.Arrays;

/**
 * 一个厨师收集了他 n 道菜的满意程度 satisfaction ，这个厨师做出每道菜的时间都是 1 单位时间。
 *
 * 一道菜的 「喜爱时间」系数定义为烹饪这道菜以及之前每道菜所花费的时间乘以这道菜的满意程度，也就是 time[i]*satisfaction[i] 。
 *
 * 请你返回做完所有菜 「喜爱时间」总和的最大值为多少。
 *
 * 你可以按 任意 顺序安排做菜的顺序，你也可以选择放弃做某些菜来获得更大的总和。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reducing-dishes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1402 {
    // 贪心的大致思路：
    // 我们将所有菜的满意程度从大到小排序；
    // 我们按照排好序的顺序依次遍历这些菜，对于当前遍历到的菜 s_i如果它与之前选择的所有菜的满意程度之和大于 0，
    // 我们就选择这道菜，否则可以直接退出遍历的循环。这是因为如果 s_i 与之前选择的所有菜的满意程度之和已经小于等于 0 了，
    // 那么后面的菜比 s_i 的满意程度还要小，就更不可能得到一个大于 0 的和了。
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int presum = 0, ans = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            int si = satisfaction[i];
            if (presum + si > 0) {
                presum += si;
                ans += presum;
            } else {
                break;
            }
        }
        return ans;
    }


    public int maxSatisfaction_DP(int[] satisfaction) { // 效率不高
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dp[i][1] = satisfaction[i];
        }
        int ans = 0;
        // dp[i][len] 以i结尾长度为len的子序列得到的最大值
        for (int i = 1; i < n; i++) {
            for (int len = 2; len <= i + 1; len++) {
                for (int j = i - 1; j >= len - 2; j--) {
                    dp[i][len] = Math.max(dp[i][len], dp[j][len - 1] + (len * satisfaction[i]));
                }
                ans = Math.max(ans, dp[i][len]);
            }
        }
        return ans;
    }
}
