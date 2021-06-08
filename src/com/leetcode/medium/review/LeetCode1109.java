package com.leetcode.medium.review;

/**
 * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
 *
 * 我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [j, k, l] 意味着我们在从 j 到 k 的每个航班上预订了 l 个座位。
 *
 * 请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/corporate-flight-bookings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-03-02
 */
public class LeetCode1109 {
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] res = new int[n];
		int[] diff = new int[n + 2];
		for (int[] book : bookings) {
			diff[book[0]] += book[2];
			diff[book[1] + 1] -= book[2];
		}
		int total = 0;
		for (int i = 0; i < n; i++) {
			total += diff[i + 1];
			res[i] =  total;
		}
		return res;
	}
}
