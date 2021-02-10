package com.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/*
找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字
 */
public class LeetCode216 {
    List<Integer> track = new LinkedList<>();
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combinationSum3(int k, int n) {
        helper(1, 9, k, n);
        return res;
    }

    void helper(int cur, int n, int k, int sum) {
        if (track.size() > k || track.size() + n - cur + 1 < k) {
            return;
        }
        if (track.size() == k) {
            int temp = 0;
            for (int val : track) {
                temp += val;
            }
            if (temp == sum) {
                res.add(new LinkedList<>(track));
                return;
            }
        }

        track.add(cur);
        helper(cur + 1, n,k,sum);
        track.remove(track.size() - 1);
        helper(cur + 1, n, k, sum);
    }
}
