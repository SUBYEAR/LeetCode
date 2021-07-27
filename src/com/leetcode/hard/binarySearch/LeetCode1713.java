package com.leetcode.hard.binarySearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个数组 target ，包含若干 互不相同 的整数，以及另一个整数数组 arr ，arr 可能 包含重复元素。
 *
 * 每一次操作中，你可以在 arr 的任意位置插入任一整数。比方说，如果 arr = [1,4,1,2] ，那么你可以在中间
 * 添加 3 得到 [1,4,3,1,2] 。你可以在数组最开始或最后面添加整数。
 *
 * 请你返回 最少 操作次数，使得 target 成为 arr 的一个子序列。
 *
 * 一个数组的 子序列 指的是删除原数组的某些元素（可能一个元素都不删除），同时不改变其余元素的相对顺序得到的数组。比
 * 方说，[2,7,4] 是 [4,2,3,7,2,1,4] 的子序列（加粗元素），但 [2,4,2] 不是子序列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1713 {
    public int minOperations(int[] target, int[] arr) {
        int n = target.length;
        Map<Integer, Integer> pos = new HashMap<>();
        for (int i = 0; i < n; i++) {
            pos.put(target[i], i);
        }
        // 使用pos数组将arr映射为位置信息数组，不在target中的元素忽略掉，考虑到target映射后是一个递增的位置数组
        // 那么求得arr映射成位置数组后的最长递增子序列的值即为所求，LeetCode300

        List<Integer> trans = new ArrayList<>();
        for (int val : arr) {
            if (pos.containsKey(val)) {
                trans.add(pos.get(val));
            }
        }
        int[] array = trans.stream().mapToInt(Integer::intValue).toArray();
        return n - lengthOfLIS(array);
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] tops = new int[n];
        int piles =0;
        for (int i = 0; i < n; i++) {
            int poker = nums[i];
            // 左侧边界的二分搜索
            int left = 0, right = piles;
            while (left < right) {
                int mid = (left + right) / 2;
                if (tops[mid] >= poker) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (left == piles) piles++;
            tops[left] = poker;
        }

        return piles;
    }
}
