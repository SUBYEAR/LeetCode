package com.leetcode.easy;

/**
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 *
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 *
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/monotonic-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode896 {
    public boolean isMonotonic(int[] A) {
        int length = A.length;
        if (length == 1) {
            return true;
        }

        int upDown = 0;
        for (int i = 1; i < length; i++) {
            if (A[i - 1] == A[i]) {
                continue;
            }
            if (upDown == 0) {
                upDown = A[i - 1] < A[i] ? 1 : -1; // 1表示上升趋势
            }

            if (!((A[i - 1] > A[i]) ^ (upDown == 1))) {
                return false;
            }
        }
        return true;
    }
}
