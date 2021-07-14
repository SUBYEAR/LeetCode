package com.leetcode.hard;

import java.util.Arrays;

/**
 * 给定一个无序的数组，找出数组在排序之后，相邻元素之间最大的差值。
 *
 * 如果数组元素个数小于 2，则返回 0。
 *
 * 示例 1:
 *
 * 输入: [3,6,9,1]
 * 输出: 3
 * 解释: 排序后的数组是 [1,3,6,9], 其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-gap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode164 {
    public int maximumGap(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return 0;
        }

        int maxVal = Arrays.stream(nums).max().getAsInt();
        int minVal = Arrays.stream(nums).min().getAsInt();
        int dis = Math.max(1, (maxVal - minVal) / (n - 1));
        int bucketSize = (maxVal - minVal) / dis + 1;
        int[][] bucket = new int[bucketSize][2];
        for (int i = 0; i < bucketSize; i++) {
            Arrays.fill(bucket[i], - 1);
        }

        for (int i = 0; i < n; i++) {
            int id = (nums[i] - minVal) / dis;
            if (bucket[id][0] == -1) {
                bucket[id][0] = bucket[id][1] = nums[i];
            } else {
                bucket[id][0] = Math.min(bucket[id][0], nums[i]);
                bucket[id][1] = Math.max(bucket[id][1], nums[i]);
            }
        }
        int res = 0;
        int pre = -1;
        for (int i = 0; i < bucketSize; i++) {
            if (bucket[i][0] == -1) {
                continue;
            }
            if (pre != -1) {
                res = Math.max(res, bucket[i][0] - bucket[pre][1]);
            }
            pre = i;
        }
        return res;
    }
}
