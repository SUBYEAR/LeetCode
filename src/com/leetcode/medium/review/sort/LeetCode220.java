package com.leetcode.medium.review.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，
 * 且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 *
 * 如果存在则返回 true，不存在返回 false。
 * <p></p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
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
        return x < 0 ? (x + 1) / w - 1 : x / w; // 或者试一试Math.floorDiv()函数
    }

    public boolean containsNearbyAlmostDuplicate_slide(int[] nums, int k, int t) {
        if (t < 0) {
            return false;
        }
        int left = 0;
        int right = 0;
        int len = nums.length;
        TreeSet<Long> map = new TreeSet<>();
        while (right < len) {
            long key = nums[right];
            if (map.contains(key)) {
                return true;
            }
            if (!map.isEmpty()) {
                Long ceilingKey = map.ceiling(key);
                if (ceilingKey != null && ceilingKey - key <= t) {
                    return true;
                }

                Long floorKey = map.floor(key);
                if (floorKey != null && key - floorKey <= t) {
                    return true;
                }
            }

            map.add(key);
            right++;

            if (right - left > k) {
                long remove = nums[left];
                map.remove(remove);
                left++;
            }

        }
        return false;
    }
}
