package com.leetcode.hard;

import java.util.Arrays;

/**
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 *
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 *
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 *
 * 注意：不允许旋转信封。
 *
 *  
 * 示例 1：
 *
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 * 示例 2：
 *
 * 输入：envelopes = [[1,1],[1,1],[1,1]]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/russian-doll-envelopes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode354 {
    public int maxEnvelopes(int[][] envelopes) { // 最长递增子序列
        Arrays.sort(envelopes, (o1, o2) -> o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0]);
        int len = envelopes.length;
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = envelopes[i][1];
        }

        return getLongIncrSeq(arr);
    }

    public int getLongIncrSeq(int[] arr) {
        int len = arr.length;
        int[] dp = new int[len]; // 以i结尾的数组最长递增子序列的长度
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        for (int i = 0; i < len; i++) {
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    /**
     * ⼀排扑克牌，我们像遍历数组那样从左到右⼀张⼀张处理这些扑
     * 克牌，最终要把这些牌分成若⼲堆。处理这些扑克牌要遵循以下规则：
     * 只能把点数⼩的牌压到点数⽐它⼤的牌上。如果当前牌点数较⼤没有可以放
     * 置的堆，则新建⼀个堆，把这张牌放进去。如果当前牌有多个堆可供选择，
     * 则选择最左边的堆放置。
     */
    // 二分法求解最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int top[] = new int[nums.length];
        int piles  = 0;
        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];

            // 二分查找
            int left = 0, right = piles;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (poker > top[mid]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 没有找到合适的牌堆，新建一个
            if (left == piles) {
                ++piles;
            }
            top[left] = poker; // 当前牌的值至于牌堆顶部
        }

        return piles;
    }

}
