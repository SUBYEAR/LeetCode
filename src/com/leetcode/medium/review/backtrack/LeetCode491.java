package com.leetcode.medium.review.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是 2 。
 * <p></p>
 * 提示：
 *
 * 给定数组的长度不会超过15。
 * 数组中的整数范围是 [-100,100]。
 * 给定数组中可能包含重复数字，相等的数字应该被视为递增的一种情况。
 */
public class LeetCode491 {
    ArrayList<Integer> temp = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        helper(nums, Integer.MIN_VALUE, 0);
        return res;
    }

    void helper(int[] nums, int last, int cur) { // last 表示上一个被选中的元素
        if (cur == nums.length) {
            if (temp.size() > 1) {
                res.add(new ArrayList<>(temp));
            }
            return;
        }

        if (nums[cur] >= last) {
            temp.add(nums[cur]);
            helper(nums, nums[cur], cur + 1);
            temp.remove(temp.size() - 1);
        }

        if (nums[cur] != last) { // 不选的时候要去重
            helper(nums, last, cur + 1);
        }
    }
}
