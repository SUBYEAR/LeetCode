package com.leetcode.medium;

/**
 * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/continuous-subarray-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-03-01
 */
public class LeetCode523 {
	public boolean checkSubarraySum(int[] nums, int k) {
		int len = nums.length;
		int[] sum = new int[len + 1];
		for (int i = 0; i < len; i++) {
			sum[i + 1] = nums[i] + sum[i];
		}

		int ans = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (k == 0 && (sum[j + 1] - sum[i] == 0)) {
					return true;
				} else if (k != 0 && ((sum[j + 1] - sum[i]) % k == 0)) {
					return true;
				}
			}
		}
		return false;
	}
}

//int sum = 0;
//
//         // key：区间 [0..i] 里所有元素的和 % k
//         // value：下标 i
//         Map<Integer, Integer> map = new HashMap<>();
//         // 理解初始化的意义
//         map.put(0, -1);
//         int len = nums.length;
//         for (int i = 0; i < len; i++) {
//             sum += nums[i];
//             if (k != 0) {
//                 sum = sum % k;
//             }
//
//             if (map.containsKey(sum)) {
//                 if (i - map.get(sum) > 1) {
//                     return true;
//                 }
//             } else {
//                 map.put(sum, i);
//             }
//
//         }
//         return false;
//
// 作者：LeetCode
// 链接：https://leetcode-cn.com/problems/continuous-subarray-sum/solution/lian-xu-de-zi-shu-zu-he-by-leetcode/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
