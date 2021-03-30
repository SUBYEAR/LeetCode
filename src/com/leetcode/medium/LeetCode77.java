package com.leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class LeetCode77 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1, new LinkedList<>());
        return res;
    }

    void backtrack(int n, int k, int cur, List<Integer> path) {
        if (path.size() + n - cur + 1 < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = cur; i <= n; i++) {
            path.add(i);
            backtrack(n, k, i + 1, path);
            path.remove(path.size() - 1);
        }
    }
}
