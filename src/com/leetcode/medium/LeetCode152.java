package com.leetcode.medium;

/**
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode152 {
    public int maxProduct(int[] nums) {
        if(nums == null ||  nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[][] dp = new int[len][2]; // 以num[i]结尾的最大值和最小值
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        int ans = dp[0][1];

        for (int i = 1; i < len; i++) {
            if (nums[i] > 0) {
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1]);
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
            } else {
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0]);
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1]);
            }

            ans = Math.max(ans, dp[i][1]);
        }

        return ans;
    }
}
