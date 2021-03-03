package com.leetcode.medium;

/**
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回
 *
 * @since 2021-03-03
 */
public class LeetCode338 {
	public int[] countBits(int num) {
		int[] res = new int[num + 1];
		for (int i = 0; i <= num; i++) {
			res[i] = getBitCount(i);
		}
		return res;

	}

	int getBitCount(int num ) {
		int count = 0;
		while (num != 0) {
			num = num & (num - 1);
			++count;
		}
		return count;
	}
}

//         int[] bits = new int[num + 1];
//         int highBit = 0;
//         for (int i = 1; i <= num; i++) {
//             if ((i & (i - 1)) == 0) {
//                 highBit = i;
//             }
//             bits[i] = bits[i - highBit] + 1;
//         }
//         return bits;
//