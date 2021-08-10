package com.leetcode.medium.review.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 *
 * 子数组 是数组中的一个连续序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode413 {
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        // 如果 nums[i]−nums[i+1]=d，那么在这一轮遍历中，j 会遍历到与上一轮相同的位置，答案增加的次数相同，
        // 并且额外多出了 nums[i−1],nums[i],nums[i+1] 这一个等差数列，因此有：t_{i+1} = t_i + 1
        int d = nums[0] - nums[1], t = 0;
        int ans = 0;
        // 因为等差数列的长度至少为 3，所以可以从 i=2 开始枚举
        for (int i = 2; i < n; ++i) {
            if (nums[i - 1] - nums[i] == d) {
                ++t;
            } else {
                d = nums[i - 1] - nums[i];
                t = 0;
            }
            ans += t;
        }
        return ans;
    }

    // 模仿#446解法，效率不够高
    public int numberOfArithmeticSlices_DP(int[] nums) {
        int n = nums.length;
        if (n < 3) {
            return 0;
        }
        int sum = 0;
        // f[i][d] 以i结尾等差为d的子数组个数
        Map<Integer, Integer>[] f = new Map[n];
        for (int i = 0; i < n; i++) {
            f[i] = new HashMap<>();
            if (i > 0) {
                int delta = nums[i] - nums[i - 1];
                int cnt = f[i - 1].getOrDefault(delta, 0);
                f[i].put(delta, cnt + 1);
                sum += cnt;
            }
        }
        return sum;
    }
}
