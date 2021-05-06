package com.leetcode.medium.review.presum;

import java.util.HashMap;

/**
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 *
 * @since 2021-03-01
 */
public class LeetCode560 {
	public int subarraySum(int[] nums, int k) {
		if (nums.length == 0) {
			return 0;
		}
		HashMap<Integer,Integer> map = new HashMap<>();
		//细节，这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
		//例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
		//输入：[3,1,1,0] k = 2时则不会漏掉
		//因为presum[3] - presum[0]表示前面 3 位的和，所以需要map.put(0,1),垫下底
		map.put(0, 1); // 这里是求个数，所以这里初始化时个数是1个

		int count = 0;
		int presum = 0;
		for (int x : nums) {
			presum += x;
			//当前前缀和已知，判断是否含有 presum - k的前缀和，那么我们就知道某一区间的和为 k 了。
			if (map.containsKey(presum - k)) {
				count += map.get(presum - k);//获取次数
			}
			//更新
			map.put(presum,map.getOrDefault(presum,0) + 1);
		}
		return count;
	}
}
