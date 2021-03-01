package com.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 *
 * @since 2021-03-01
 */
public class LeetCode303 {
	private class NumArray {
		Map<Integer, Integer> map = new HashMap<>();
		int[] nums;
		public NumArray(int[] nums) {
			this.nums = new int[nums.length];
			int sum = 0;
			for (int i = 0; i < nums.length; i++) {
				sum += nums[i];
				map.put(i, sum);
				this.nums[i] = nums[i];
			}
		}

		public int sumRange(int i, int j) {
			return nums[i] + map.get(j) - map.get(i);
		}
	}
}
