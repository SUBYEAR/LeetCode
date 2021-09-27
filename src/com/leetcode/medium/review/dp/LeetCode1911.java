package com.leetcode.medium.review.dp;

/**
 * 一个下标从 0 开始的数组的 交替和 定义为 偶数 下标处元素之 和 减去 奇数 下标处元素之 和 。
 *
 * 比方说，数组 [4,2,5,3] 的交替和为 (4 + 5) - (2 + 3) = 4 。
 * 给你一个数组 nums ，请你返回 nums 中任意子序列的 最大交替和 （子序列的下标 重新 从 0 开始编号）。
 *
 * 一个数组的 子序列 是从原数组中删除一些元素后（也可能一个也不删除）剩余元素不改变顺序组成的数组。比方说，[2,7,4] 是 
 * [4,2,3,7,2,1,4] 的一个子序列（加粗元素），但是 [2,4,2] 不是。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-alternating-subsequence-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1911 {
    public long maxAlternatingSum(int[] nums) {
//        int n = nums.length; // 这种dp定义方式会导致超时
//        long[][] dp = new long[n][2]; // 0 表示以i为偶数下标结束的交替和, 1 表示以i为奇数下标结束的交替和
//        for (int i = 0; i < n; i++) {
//            Arrays.fill(dp[i], Integer.MIN_VALUE);
//        }
//        dp[0][0] = nums[0];
//        dp[0][1] = 0;
//        long ans =  Math.max(dp[0][0], dp[0][1]);
//
//        for (int i = 1; i < n ;i++) {
//            dp[i][0] = nums[i];
//            for (int j = i - 1; j >= 0; j--) {
//                dp[i][0] = Math.max(dp[i][0], dp[j][1] + nums[i]);
//                dp[i][1] = Math.max(dp[i][1], dp[j][0] - nums[i]);
//            }
//            ans = Math.max(ans, Math.max(dp[i][0], dp[i][1]));
//        }
//        return ans;

        // 不用下标来指示结束
        // 记 odd[i] 表示我们在数组nums的前缀nums[0..i]中选择元素组成子序列，且最后一个选择的元素的下标是奇数时，可以得到的最大交替和
        // 同理even[i]的定义也是类似
        // 在状态转移方程中odd[i]和 even[i] 都只会从odd[i−1]和even[i−1] 转移而来，因此我们可以使用两个变量代替这两个二维数组进行状态转移

        int n = nums.length;
        long odd = 0, even = nums[0];
        for (int i = 1; i < n; i++) {
            odd = Math.max(odd, even - nums[i]);
            even = Math.max(even, odd + nums[i]);
        }
        return even;
    }
}
