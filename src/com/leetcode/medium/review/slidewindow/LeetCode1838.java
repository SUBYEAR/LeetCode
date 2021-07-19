package com.leetcode.medium.review.slidewindow;

import java.util.Arrays;

/**
 * 元素的 频数 是该元素在一个数组中出现的次数。
 *
 * 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。
 *
 * 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,4], k = 5
 * 输出：3
 * 解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
 * 4 是数组中最高频元素，频数是 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frequency-of-the-most-frequent-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1838 {
    public int maxFrequency(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        int[] preSum = new int[len + 1];
        int res = 1;
        int record = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int left = 0, right = 0;
        while (right < len) {
            record = k - ((right - left) * nums[right] - (preSum[right] - preSum[left]));
            while (left < len && record < 0) {
                record += nums[right] - nums[left];
                left++;
            }
            res = Math.max(res, right - left + 1);
            ++right;
        }

        return res;
    }
}
