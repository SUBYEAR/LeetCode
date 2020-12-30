/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
 *
 * @since 2020-06-09
 */
public class LeetCode220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) {
            return false;
        }
        Map<Long, Long> map = new HashMap<>(); // 用一个标签为桶序号的散列表就可以了。
        long w = (long) t + 1;
        for (int i = 0; i < nums.length; i++) {
            long m = getId(nums[i], w);
            if (map.containsKey(m)) {
                return true;
            }
            if (map.containsKey(m - 1) && Math.abs(nums[i] - map.get(m - 1)) < w) {
                return true;
            }
            if (map.containsKey(m + 1) && Math.abs(nums[i] - map.get(m + 1)) < w) {
                return true;
            }
            map.put(m, (long) nums[i]);
            if (i >= k) {
                map.remove(getId(nums[i - k], w));
            }
        }

        return false;
    }

    // Get the ID of the bucket from element value x and bucket width w
    // In Java, `-3 / 5 = 0` and but we need `-3 / 5 = -1`.
    private long getId(long x, long w) {
        return x < 0 ? (x + 1) / w - 1 : x / w;
    }

}
