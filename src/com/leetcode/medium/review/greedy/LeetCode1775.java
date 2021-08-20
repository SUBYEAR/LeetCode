package com.leetcode.medium.review.greedy;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * 给你两个长度可能不等的整数数组 nums1 和 nums2 。两个数组中的所有值都在 1 到 6 之间（包含 1 和 6）。
 *
 * 每次操作中，你可以选择 任意 数组中的任意一个整数，将它变成 1 到 6 之间 任意 的值（包含 1 和 6）。
 *
 * 请你返回使 nums1 中所有数的和与 nums2 中所有数的和相等的最少操作次数。如果无法使两个数组的和相等，请返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/equal-sum-arrays-with-minimum-number-of-operations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1775 {
    int[] sum = new int[2];
    int maxIdx = 0;

    public int minOperations(int[] nums1, int[] nums2) {
        sum[0] = Arrays.stream(nums1).reduce(Integer::sum).orElse(0);
        sum[1] = Arrays.stream(nums2).reduce(Integer::sum).orElse(0);
        if (sum[0] == sum[1]) {
            return 0;
        }

        TreeMap<Integer, Integer>[] freq = new TreeMap[2];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new TreeMap<>();
        }
        for (int i = 0; i < nums1.length; i++) {
            freq[0].put(nums1[i], freq[0].getOrDefault(nums1[i], 0) + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            freq[1].put(nums2[i], freq[1].getOrDefault(nums2[i], 0) + 1);
        }
        if (sum[0] < sum[1]) {
            maxIdx = 1;
        }

        int ans = 0;
        while (sum[maxIdx] > sum[1 - maxIdx]) {
            if (process(freq) == 1) {
                return -1;
            }
            ++ans;
        }
        return ans;
    }

    int process(TreeMap<Integer, Integer> freq[]) {
        Integer high = freq[maxIdx].lastKey();
        Integer low = freq[1 - maxIdx].firstKey();
        if (high == 1 && low == 6) {
            return 1;
        }
        if (high - 1 > 6 - low) {
            update(freq[maxIdx], high);
            freq[maxIdx].put(1, freq[maxIdx].getOrDefault(1, 0) + 1);
            sum[maxIdx] = sum[maxIdx] - high + 1;
        } else {
            update(freq[1 - maxIdx], low);
            freq[1 - maxIdx].put(6, freq[maxIdx].getOrDefault(6, 0) + 1);
            sum[1 - maxIdx] = sum[1 - maxIdx] + 6 - low;
        }

        return 0;
    }

    void update(TreeMap<Integer, Integer> freq, int key) {
        Integer cnt = freq.get(key);
        if (cnt == 1) {
            freq.remove(key);
        } else {
            freq.put(key, cnt - 1);
        }
    }
}

