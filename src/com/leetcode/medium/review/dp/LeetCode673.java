package com.leetcode.medium.review.dp;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 *
 * 示例 1:
 *
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode673 {
    // dp[i]表示以nums[i]为结尾的字符串，最长递增子序列的个数为count[i]
    // 那么在nums[i] > nums[j]前提下，如果在[0, i-1]的范围内，找到了j，使得dp[j] + 1 > dp[i]，说明找到了一个更长的递增子序列。
    // 那么以i为结尾的子串的最长递增子序列的个数，就是最新的以j为结尾的子串的最长递增子序列的个数，即：count[i] = count[j]。
    // 在nums[i] > nums[j]前提下，如果在[0, i-1]的范围内，找到了j，使得dp[j] + 1 == dp[i]，说明找到了两个相同长度的递增子序列。
    // 那么以i为结尾的子串的最长递增子序列的个数 就应该加上以j为结尾的子串的最长递增子序列的个数，即：count[i] += count[j];
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        if (N <= 1) return N;
        int[] dp = new int[N]; //dp[i] = length of longest ending in nums[i]
        int[] count = new int[N]; //count[i] = number of longest ending in nums[i]
        Arrays.fill(count, 1);
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    if (dp[j] + 1 > dp[i]) { // 找到了一个更长的递增子序列
                        dp[i] = dp[j] + 1; // 更新dp的逻辑放在这里了 等价于下面的max操作
                        count[i] = count[j];
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];
                    }
                    // dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0, ans = 0;
        for (int length: dp) {
            longest = Math.max(longest, length);
        }
        for (int i = 0; i < N; ++i) {
            if (dp[i] == longest) {
                ans += count[i];
            }
        }
        return ans;
    }
}
