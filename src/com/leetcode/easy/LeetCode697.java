package com.leetcode.easy;

/**
 * 给定一个非空且只包含非负数的整数数组 nums，数组的度的定义是指数组里任一元素出现频数的最大值。
 *
 * 你的任务是在 nums 中找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 *
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/degree-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-20
 */
public class LeetCode697 {
    public int findShortestSubArray(int[] nums) {
        int[] freq = new int[50000];
        int len = nums.length;
        int maxCount = 0;
        for (int i = 0; i < len; i++) {
            freq[nums[i]]++;
            maxCount = Math.max(maxCount, freq[nums[i]]);
        }
        int res = len;
        for (int i = 0; i < 50000; i++) {
            if (freq[i] == maxCount) {
                res = Math.min(res,getDistance(nums, i));
            }
        }


        return res;
    }

    int getDistance(int[] nums, int value) {
        int len = nums.length;
        int left, right;
        for (left = 0; left < len; left++) {
            if (nums[left] == value) {
                break;
            }
        }

        for (right = len - 1; right >= 0; right--) {
            if (nums[right] == value) {
                break;
            }
        }

        return right - left + 1;
    }
}
