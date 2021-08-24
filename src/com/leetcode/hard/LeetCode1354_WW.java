package com.leetcode.hard;

/**
 * 给你一个整数数组 target 。一开始，你有一个数组 A ，它的所有元素均为 1 ，你可以执行以下操作：
 *
 * 令 x 为你数组里所有元素的和
 * 选择满足 0 <= i < target.size 的任意下标 i ，并让 A 数组里下标为 i 处的值为 x 。
 * 你可以重复该过程任意次
 * 如果能从 A 开始构造出目标数组 target ，请你返回 True ，否则返回 False 。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-target-array-with-multiple-sums
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1354_WW {
    public boolean isPossible(int[] target) {
        // 注意到这个操作是可逆的，即数组中最大的数肯定是最后一次被替换后的数，也即上一轮的数组和，我们记这个最大的数为 s ，
        // 这一轮的数组和为 sum ，则我们可以知道被替换的数就是 s-(sum-s)
        return false;
    }
}
