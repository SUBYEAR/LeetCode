package com.leetcode.medium.review.greedy;

import java.util.Arrays;

/**
 * 给你一个正整数数组 arr 。请你对 arr 执行一些操作（也可以不进行任何操作），使得数组满足以下条件：
 * arr 中 第一个 元素必须为 1 。
 * 任意相邻两个元素的差的绝对值 小于等于 1 ，也就是说，对于任意的 1 <= i < arr.length （数组下标从 0 开始），
 * 都满足 abs(arr[i] - arr[i - 1]) <= 1 。abs(x) 为 x 的绝对值。
 * 你可以执行以下 2 种操作任意次：
 *
 * 减小 arr 中任意元素的值，使其变为一个 更小的正整数 。
 * 重新排列 arr 中的元素，你可以以任意顺序重新排列。
 * 请你返回执行以上操作后，在满足前文所述的条件下，arr 中可能的 最大值 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-element-after-decreasing-and-rearranging
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1846 {
    public int maximumElementAfterDecrementingAndRearranging_(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        int expected = 1; // 每遍历到数组中的一个位置期望数组当前元素的值是expected
        int ans = 1;
        // 数组最大值在最好的情况下是数组是升序排列且元素间的值相差一，遍历每个元素如果元素如果比期望值大那么肯定可以通过减小操作达到期望值
        // 那么此时期望值加1，并更新返回值
        for (int i = 0; i < len; i++) {
            if (arr[i] >= expected) {
                ans = Math.min(arr[i], expected);
                ++expected;
            }
        }
        return ans;
    }

    // 官方解法的思路是数组进行升序排序，随后对数组进行遍历，将 arr[i] 更新为其和 arr[i−1] + 1中的较小值即可。
    // 最终的答案（最大值）即为 arr 中的最后一个元素
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int len = arr.length;
        Arrays.sort(arr);
        arr[0] = 1;
        for (int i = 1; i < len; ++i) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        return arr[len - 1];
    }
}
