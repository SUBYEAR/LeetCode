package com.leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class LeetCode78 {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
//        res.add(new LinkedList<>());
        backtrack(nums, 0, new LinkedList<>());
        return res;
    }

    void backtrack(int[] nums, int start, List<Integer> path) {
//        if (start == nums.length) { // 这里的终止条件只是返回，没有收集结果所以可以优化掉
//            return;
//        }
        res.add(new LinkedList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
