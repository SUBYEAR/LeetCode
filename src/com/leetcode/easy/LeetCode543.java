package com.leetcode.easy;

import com.leetcode.TreeNode;

/*
给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 */
public class LeetCode543 {
    int res;

    public int diameterOfBinaryTree(TreeNode root) {
        res = 1;
        getNum(root);
        return res - 1;
    }

    int getNum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = getNum(root.left);
        int r = getNum(root.right);
        res = Math.max(res, l + r + 1);
        return Math.max(l, r) + 1;
    }
}
