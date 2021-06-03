package com.leetcode.medium.review.presum;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: nums = [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contiguous-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode525 {
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        return getSubArrLength(nums, 0);
    }

    int getSubArrLength(int[] arr, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, -1);
        int pre = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            pre += arr[i];
            if (map.containsKey(pre)) {
                res = Math.max(res, i - map.get(pre));
            }
            map.putIfAbsent(pre, i);
        }
        return res;
    }
}
