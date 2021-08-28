package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode129 {
    List<Integer> list = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return list.stream().reduce(Integer::sum).orElse(0);
    }

    void dfs(TreeNode root, int cur) {
        if (root == null) {
            return;
        }
        int num = root.val + cur * 10;
        if (root.left == null && root.right == null) {
            list.add(num);
        }
        dfs(root.left, num);
        dfs(root.right, num);
    }
}
