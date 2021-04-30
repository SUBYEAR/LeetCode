package com.leetcode.easy;

import com.leetcode.TreeNode;

/**
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和。
 */
public class LeetCode938 {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        int left = rangeSumBST(root.left, low, high);
        int right = rangeSumBST(root.right, low, high);
        int cur = root.val >= low && root.val <= high ? root.val : 0;
        return cur + left + right;
    }
}
