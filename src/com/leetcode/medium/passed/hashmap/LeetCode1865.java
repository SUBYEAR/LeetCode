package com.leetcode.medium.passed.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个整数数组 nums1 和 nums2 ，请你实现一个支持下述两类查询的数据结构：
 *
 * 累加 ，将一个正整数加到 nums2 中指定下标对应元素上。
 * 计数 ，统计满足 nums1[i] + nums2[j] 等于指定值的下标对 (i, j) 数目（0 <= i < nums1.length 且 0 <= j < nums2.length）。
 * 实现 FindSumPairs 类：
 *
 * FindSumPairs(int[] nums1, int[] nums2) 使用整数数组 nums1 和 nums2 初始化 FindSumPairs 对象。
 * void add(int index, int val) 将 val 加到 nums2[index] 上，即，执行 nums2[index] += val 。
 * int count(int tot) 返回满足 nums1[i] + nums2[j] == tot 的下标对 (i, j) 数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/finding-pairs-with-a-certain-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1865 {
    int[] nums1;
    int[] nums2;
    Map<Integer, Integer> freq = new HashMap<>();
    public LeetCode1865(int[] nums1, int[] nums2) {
        // 1 <= nums1[i] <= 10^9
        // 1 <= nums2[i] <= 10^5
        this.nums1 = nums1; // 1 <= nums1.length <= 1000
        Arrays.sort(nums1);

        this.nums2 = nums2; // 1 <= nums2.length <= 10^5
        for (int num : nums2) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
    }

    public void add(int index, int val) {
        int newVal = nums2[index] + val;
        freq.put(newVal, freq.getOrDefault(newVal, 0) + 1);
        Integer cnt = freq.get(nums2[index]);
        if (cnt != null) {
            if (cnt == 1) {
                freq.remove(nums2[index]);
            } else {
                --cnt;
                freq.put(nums2[index], cnt);
            }
        }
        nums2[index] = newVal;
    }

    public int count(int tot) {
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            int target = tot - nums1[i];
            if (target <= 0) {
                break;
            }
            ans += freq.getOrDefault(target, 0);
        }
        return ans;
    }
}
