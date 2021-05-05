package com.leetcode.medium.review.backtrack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
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

// 回溯标准模板写法
// import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Deque;
//import java.util.List;
//
//public class Solution {
//
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        int len = candidates.length;
//        List<List<Integer>> res = new ArrayList<>();
//        if (len == 0) {
//            return res;
//        }
//
//        // 排序是剪枝的前提
//        Arrays.sort(candidates);
//        Deque<Integer> path = new ArrayDeque<>();
//        dfs(candidates, 0, len, target, path, res);
//        return res;
//    }
//
//    private void dfs(int[] candidates, int begin, int len, int target, Deque<Integer> path, List<List<Integer>> res) {
//        // 由于进入更深层的时候，小于 0 的部分被剪枝，因此递归终止条件值只判断等于 0 的情况
//        if (target == 0) {
//            res.add(new ArrayList<>(path));
//            return;
//        }
//
//        for (int i = begin; i < len; i++) {
//            // 重点理解这里剪枝，前提是候选数组已经有序，
//            if (target - candidates[i] < 0) {
//                break;
//            }
//
//            path.addLast(candidates[i]);
//            dfs(candidates, i, len, target - candidates[i], path, res);
//            path.removeLast();
//        }
//    }
//}

// 官方解法
// class Solution {
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> ans = new ArrayList<List<Integer>>();
//        List<Integer> combine = new ArrayList<Integer>();
//        dfs(candidates, target, ans, combine, 0);
//        return ans;
//    }
//
//    public void dfs(int[] candidates, int target, List<List<Integer>> ans, List<Integer> combine, int idx) {
//        if (idx == candidates.length) {
//            return;
//        }
//        if (target == 0) {
//            ans.add(new ArrayList<Integer>(combine));
//            return;
//        }
//        // 直接跳过
//        dfs(candidates, target, ans, combine, idx + 1);
//        // 选择当前数
//        if (target - candidates[idx] >= 0) {
//            combine.add(candidates[idx]);
//            dfs(candidates, target - candidates[idx], ans, combine, idx);
//            combine.remove(combine.size() - 1);
//        }
//    }
//}
