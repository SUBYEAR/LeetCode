package com.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
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
