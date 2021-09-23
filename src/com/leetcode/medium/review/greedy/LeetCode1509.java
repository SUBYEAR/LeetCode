package com.leetcode.medium.review.greedy;

import java.util.Arrays;

/**
 * 给你一个数组 nums ，每次操作你可以选择 nums 中的任意一个元素并将它改成任意值。
 *
 * 请你返回三次操作后， nums 中最大值与最小值的差的最小值。
 */
public class LeetCode1509 {
    // 修改必然是将最大值改小，或者将最小值改大，这样才能让最大值与最小值的差尽可能小
    // 只需要找到最大的四个数与最小的四个数即可。当我们删去最小的(0≤k≤3) 个数，还需要删去 3-k 个最大值。枚举这四种情况即可。
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n <= 4) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            ans = Math.min(ans, nums[n - 4 + i] - nums[i]);
        }
        return ans;
    }
}
// 用两个数组分别记录最大值与最小值，不断更新这两个最值数组。
// public int minDifference(int[] nums) {
//        int n = nums.length;
//        if (n <= 4) {
//            return 0;
//        }
//
//        int[] maxn = new int[4];
//        int[] minn = new int[4];
//        Arrays.fill(maxn, -1000000000);
//        Arrays.fill(minn, 1000000000);
//        for (int i = 0; i < n; i++) {
//            int add = 0;
//            while (add < 4 && maxn[add] > nums[i]) { // 索引在前值越大
//                add++;
//            }
//            if (add < 4) {
//                for (int j = 3; j > add; j--) {
//                    maxn[j] = maxn[j - 1]; // 整体后移出一个空位插入值
//                }
//                maxn[add] = nums[i];
//            }
//            add = 0;
//            while (add < 4 && minn[add] < nums[i]) {
//                add++;
//            }
//            if (add < 4) {
//                for (int j = 3; j > add; j--) {
//                    minn[j] = minn[j - 1];
//                }
//                minn[add] = nums[i];
//            }
//        }
//        int ret = Integer.MAX_VALUE;
//        for (int i = 0; i < 4; i++) {
//            ret = Math.min(ret, maxn[i] - minn[3 - i]);
//        }
//        return ret;
//    }
