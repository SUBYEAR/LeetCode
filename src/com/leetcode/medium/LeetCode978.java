package com.leetcode.medium;

import java.util.Arrays;

/**
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 *
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 返回 A 的最大湍流子数组的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-turbulent-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2021-02-10
 */
public class LeetCode978 {
    public int maxTurbulenceSize(int[] arr) {
        int res = 1;
        int[][] dp = new int[2][arr.length];
        Arrays.fill(dp[0], 1);
        Arrays.fill(dp[1], 1);
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                dp[0][i] = dp[1][i -1] + 1;
            } else if (arr[i] < arr[i - 1]) {
                dp[1][i] = dp[0][i -1] + 1;
            }
            res = Math.max(res, Math.max(dp[0][i], dp[1][i]));
        }
        return res;
    }

    boolean isUpDown(int[] arr, int start, int end) {
        int flag = -1; // 上升趋势是1.下降趋势是0
        for (int i = start + 1; i <= end && i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }

            int compare = arr[i - 1] > arr[i] ? 0 : 1;
            if (i < start + 2) {
                flag = compare;
                continue;
            }
            if ((compare + flag) != 1) {
                return false;
            }
            flag = compare;
        }
        return true;
    }

    // 滑动窗口解法
    //         int n = arr.length;
    //         int ret = 1;
    //         int left = 0, right = 0;
    //
    //         while (right < n - 1) {
    //             if (left == right) {
    //                 if (arr[left] == arr[left + 1]) {
    //                     left++;
    //                 }
    //                 right++;
    //             } else {
    //                 if (arr[right - 1] < arr[right] && arr[right] > arr[right + 1]) {
    //                     right++;
    //                 } else if (arr[right - 1] > arr[right] && arr[right] < arr[right + 1]) {
    //                     right++;
    //                 } else {
    //                     left = right;
    //                 }
    //             }
    //             ret = Math.max(ret, right - left + 1);
    //         }
    //         return ret;
}
