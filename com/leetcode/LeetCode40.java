package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。
 */
public class LeetCode40 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, new LinkedList<>());
        return res;

    }

    void backtrack(int[] arr, int start, int target, List<Integer> track) {
        if (target == 0) {
            if (res.contains(track)) {
                return;
            }
            res.add(new LinkedList<>(track));
            return;
        }
        if (start == arr.length || arr[start] > target || target < 0) {
            return;
        }

        track.add(arr[start]);
        backtrack(arr, start + 1, target - arr[start], track);
        track.remove(track.size() - 1);
        backtrack(arr, start + 1, target, track);
    }
}
