package com.leetcode.medium.review.presum;

/**
 * 给你一个整数数组 nums 。你需要选择 恰好 一个下标（下标从 0 开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
 *
 * 比方说，如果 nums = [6,1,7,4,1] ，那么：
 *
 * 选择删除下标 1 ，剩下的数组为 nums = [6,7,4,1] 。
 * 选择删除下标 2 ，剩下的数组为 nums = [6,1,4,1] 。
 * 选择删除下标 4 ，剩下的数组为 nums = [6,1,7,4] 。
 * 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。
 *
 * 请你返回删除操作后，剩下的数组 nums 是 平衡数组 的 方案数 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ways-to-make-a-fair-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1664 {
    // 链接：https://leetcode-cn.com/problems/ways-to-make-a-fair-array/solution/javachao-yue-100zhen-zheng-de-da-shi-yon-er4i/
    // 当你删i下标元素时，i前的下标奇偶性不变，i后面下标的奇偶性互换.左奇+右偶==左偶+右奇，就是符合题意的情况
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int[] odd = new int[n];
        int[] even = new int[n];
        even[0] = nums[0];
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                even[i] = even[i - 1] + nums[i];
                odd[i] = odd[i - 1];
            } else {
                odd[i] = odd[i - 1] + nums[i];
                even[i] = even[i - 1];
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int left_odd = i - 1 >= 0 ? odd[i - 1] : 0;
            int left_even = i - 1 >= 0 ? even[i - 1] : 0;

            int right_odd = odd[n - 1] - odd[i];
            int right_even = even[n - 1] - even[i];

            if (left_odd + right_even == left_even + right_odd)
                res++;
        }
        return res;
    }
}
