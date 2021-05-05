package com.leetcode.medium.review.slidewindow;

import java.util.TreeMap;

/**
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，
 * 该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit 。
 *
 * 如果不存在满足条件的子数组，则返回 0 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1438 {
    public int longestSubarray(int[] nums, int limit) {
        int len = nums.length;
        int res = 0;
        int left = 0, right = 0;
        TreeMap<Integer,Integer> map = new TreeMap();
        while (right < len) {
            int count = map.getOrDefault(nums[right], 0);
            map.put(nums[right], count + 1);
            right++;

            while (map.lastKey() - map.firstKey() > limit) {
                int n = map.get(nums[left]);
                if (n == 1) {
                    map.remove(nums[left]);
                } else {
                    map.put(nums[left], n - 1);
                }
                ++left;
            }
            res = Math.max(res, right - left);
        }

        return res;
    }
}
