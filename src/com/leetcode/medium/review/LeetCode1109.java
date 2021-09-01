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
public class LeetCode1109 { // 考察知识点是差分
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

	// 官解
	// 对于预定记录booking=[l,r,inc]，我们需要让 d[l-1]增加 inc，d[r] 减少inc。
	// 特别地，当 r 为 n 时，我们无需修改 d[r]，因为这个位置溢出了下标范围。如果求前缀和时考虑该位置，那么该位置对应的前缀和值必定为 0
	// 因为给定的航班号是从1下标开始所以航班n映射到差分数组的n-1位置
	// 差分数组中n-1是最后一个位置，下一个位置n和前一个位置的差是-(n-1位置上的值),这样在累加求和至差分数组的n位置时其值必然为0
	// 或者还有一直理解差分数组中n位置是航班号的n+1,在这个航班号上还没有座位被遇到所以其差分值为0
	public int[] corpFlightBookings_diff(int[][] bookings, int n) {
		int[] nums = new int[n];
		for (int[] booking : bookings) {
			nums[booking[0] - 1] += booking[2];
			if (booking[1] < n) {
				nums[booking[1]] -= booking[2];
			}
		}
		for (int i = 1; i < n; i++) {
			nums[i] += nums[i - 1];
		}
		return nums;
	}
}
