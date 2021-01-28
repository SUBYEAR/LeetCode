package com.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。
解集不能包含重复的组合。
 */
public class LeetCode39 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, new LinkedList<>());
        return res;
    }

    int leftBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (target <= arr[mid]) {
                right = mid ;
            } else  {
                left = mid + 1;
            }
        }

        return left;
    }

    void backtrack(int[] arr, int start, int target, List<Integer> track) {
        if (target == 0) {
            res.add(new LinkedList<>(track));
            return;
        }
        if (start == arr.length) {
            return;
        }
        if (target < 0 || arr[start] > target) {
            return;
        }



        track.add(arr[start]);
        backtrack(arr, start, target - arr[start], track);
        track.remove(track.size() - 1);
        backtrack(arr, start + 1, target, track);

    }
}
