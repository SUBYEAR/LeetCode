package com.leetcode.hard.dp;

import java.util.Arrays;

/**
 * 给你两个整数数组 nums1 和 nums2 ，它们长度都为 n 。
 *
 * 两个数组的 异或值之和 为 (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) （下标从 0 开始）。
 *
 * 比方说，[1,2,3] 和 [3,2,1] 的 异或值之和 等于 (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4 。
 * 请你将 nums2 中的元素重新排列，使得 异或值之和 最小 。
 *
 * 请你返回重新排列之后的 异或值之和 。
 * 提示：
  *
  * n == nums1.length
  * n == nums2.length
  * 1 <= n <= 14
  * 0 <= nums1[i], nums2[i] <= 107
  *
  * 来源：力扣（LeetCode）
  * 链接：https://leetcode-cn.com/problems/minimum-xor-sum-of-two-arrays
  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1879_W {
    public int minimumXORSum(int[] nums1, int[] nums2) {
        // 用长度为n的二进制数mask表示数组nums2中的数被选择的状态如果mask从低到高的第i位为1，说明 nums[i] 已经被选择，否则说明其未被选择。
        // f[mask]表示当选择了数组nums2中的元素状态为mask，并且选择了数组nums1的前count(mask)个元素 的情况下组成的最小的异或值之和
        // 枚举nums1[c - 1]与nums2中的哪一个元素进行异或运算假设为nums2[i]
        // 枚举mask二进制表示中为1的位 状态转移方程为f[mask] = min(f[mask\i] + (nums1[c- 1] ^ nums2[i])) 其中mask\i表示将mask的第i位从1变为0
        int n = nums1.length;
        int[] f = new int[1 << n];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        for (int mask = 1; mask < (1 << n); mask++) {
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    f[mask] = Math.min(f[mask], f[mask ^ (1 << i)] + (nums1[Integer.bitCount(mask) - 1] ^ nums2[i]));
                }
            }
        }

        return f[f.length - 1];
    }
}
