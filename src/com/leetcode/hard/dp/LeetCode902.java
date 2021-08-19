package com.leetcode.hard.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 我们有一组排序的数字 D，它是  {'1','2','3','4','5','6','7','8','9'} 的非空子集。（请注意，'0' 不包括在内。）
 * <p>
 * 现在，我们用这些数字进行组合写数字，想用多少次就用多少次。例如 D = {'1','3','5'}，我们可以写出像 '13', '551', '1351315' 这样的数字。
 * <p>
 * 返回可以用 D 中的数字写出的小于或等于 N 的正整数的数目。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/numbers-at-most-n-given-digit-set
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode902 {
    // 官解
    public int atMostNGivenDigitSet_dp(String[] D, int N) {
        // 我们用 dp[i] 表示小于等于 N 中最后 |N| - i 位数的合法数的个数
        String S = String.valueOf(N);
        int K = S.length();
        int[] dp = new int[K+1];
        dp[K] = 1;

        for (int i = K-1; i >= 0; --i) {
            // compute dp[i]
            int Si = S.charAt(i) - '0';
            for (String d: D) {
                if (Integer.valueOf(d) < Si)
                    dp[i] += Math.pow(D.length, K-i-1);
                else if (Integer.valueOf(d) == Si)
                    dp[i] += dp[i+1];
            }
        }

        for (int i = 1; i < K; ++i)
            dp[0] += Math.pow(D.length, i); // 最后加上小于k位的结果，前面的dp过程是在处理等于k位的情况
        return dp[0];
    }

    // 提交了5次才通过
    public int atMostNGivenDigitSet(String[] digits, int n) {
        int[] nums = Arrays.stream(digits).mapToInt(Integer::parseInt).toArray();
        int k = nums.length;
        long minNum = nums[0]; // 越界有坑
        long maxNum = nums[nums.length - 1];
        long preMax = maxNum;
        int level = 1; // 表示k叉树的最后一层
        while (minNum <= n) {
            level++;
            minNum = minNum * 10 + nums[0];
            preMax = maxNum;
            maxNum = maxNum * 10 + nums[nums.length - 1];
        }
        level--;
        if (n >= preMax || level == 0) { // 位于上一层的最大值和下一层的最小值之间
            return get(k, level); // level 层k叉树的总节点
        }

        int ans = get(k, level - 1);
        while (n > 0) {
            int pow = (int) Math.pow(10, level - 1);
            int high = n / pow;
            if (high == 0) { // 等于0可以直接返回没有考虑到
                break; // 位于上一层的最大值和下一层的最小值之间
            }
            n %= pow;
            int i = leftBound(nums, high);
            if (level == 1 && i < k && nums[i] == high) i += 1; // 这个条件不加结果会少1
            ans += (i * (int) Math.pow(k, level - 1));
            if (i < k && nums[i] != high) break; // 这里没找到可以停靠的树节点可以返回
            level--;
        }

        return ans;
    }

    int leftBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (target <= arr[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    int get(int k, int level) {
        int ans = 0;
        int multi = 1;
        while (level > 0) {
            multi *= k;
            ans += multi;
            level--;
        }
        return ans;
    }
}
