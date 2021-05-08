package com.leetcode.medium.review.dp;

/**
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。
 * 之后，你必须删除每个等于 nums[i] - 1 或 nums[i] + 1 的元素。
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-and-earn
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode740 {
    public int deleteAndEarn(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        int[] sum = new int[maxVal + 1];

        for (int num : nums) {
            sum[num] += num;
        }

        return rob(sum);
    }

    private int rob(int[] nums) {
        int len = nums.length;
        int first = nums[0], second = Math.max(nums[1], nums[0]);

        for (int i = 2; i < len; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
