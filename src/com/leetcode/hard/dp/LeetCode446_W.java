package com.leetcode.hard.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * 如果一个数列至少有三个元素，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 *
 * 例如，以下数列为等差数列:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * 以下数列不是等差数列。
 *
 * 1, 1, 2, 5, 7
 *  
 *
 * 数组 A 包含 N 个数，且索引从 0 开始。该数组子序列将划分为整数序列 (P0, P1, ..., Pk)，满足 0 ≤ P0 < P1 < ... < Pk < N。
 *
 *  
 *
 * 如果序列 A[P0]，A[P1]，...，A[Pk-1]，A[Pk] 是等差的，那么数组 A 的子序列 (P0，P1，…，PK) 称为等差序列。值得注意的是，这意味着 k ≥ 2。
 *
 * 函数要返回数组 A 中所有等差子序列的个数。
 *
 * 输入包含 N 个整数。每个整数都在 -231 和 231-1 之间，另外 0 ≤ N ≤ 1000。保证输出小于 231-1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/arithmetic-slices-ii-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode446_W {
    public int numberOfArithmeticSlices(int[] nums) {
// 弱等差数列 是至少有两个元素的子序列，任意两个连续元素的差相等。
        // f[i][d] 表示以nums[i] 结尾公差为d的弱等差数列个数
        // f[i][A[i]-A[j]] += f[j][A[i]-A[j]] + 1 ; 1表示新增的弱等差数列
        int n = nums.length;
        long ans = 0;
        Map<Integer, Integer>[] cnt = new Map[n];
        for (int i = 0; i < n; i++) {
            cnt[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long) nums[i] - nums[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int sum = cnt[j].getOrDefault(diff, 0); // f[j][A[i]-A[j]]
                int origin = cnt[i].getOrDefault(diff, 0); // f[i][A[i]-A[j]]
                cnt[i].put(diff, sum + origin + 1); // f[i][A[i]-A[j]] += f[j][A[i]-A[j]] + 1
                ans += sum;
            }
        }

        return (int) ans;
    }
}
