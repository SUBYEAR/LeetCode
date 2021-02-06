package com.leetcode.easy;

/**
 * 给你一个整数数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 *
 * 数组 中心索引 是数组的一个索引，其左侧所有元素相加的和等于右侧所有元素相加的和。
 *
 * 如果数组不存在中心索引，返回 -1 。如果数组有多个中心索引，应该返回最靠近左边的那一个。
 *
 * 注意：中心索引可能出现在数组的两端。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-pivot-index
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LeetCode724 {
    public int pivotIndex(int[] nums) {
        int length = nums.length;
        int[] left = new int[length];
        int[] right = new int[length];
        for (int i = 1; i < length; i++) {
            left[i] += (left[i -1] + nums[i - 1]);
        }

        for (int i = length - 1; i > 0; i--) {
            right[i - 1] += (right[i] + nums[i]);
        }

        for (int i = 0; i < length; i++) {
            if (left[i] == right[i]) {
                return i;
            }
        }
        return -1;
    }
}
