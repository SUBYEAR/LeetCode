package com.leetcode.medium;

import com.leetcode.TreeNode;

/*
给你一棵以 root 为根的二叉树，二叉树中的交错路径定义如下：

选择二叉树中 任意 节点和一个方向（左或者右）。
如果前进方向为右，那么移动到当前节点的的右子节点，否则移动到它的左子节点。
改变前进方向：左变右或者右变左。
重复第二步和第三步，直到你在树中无法继续移动。
交错路径的长度定义为：访问过的节点数目 - 1（单个节点的路径长度为 0 ）。

请你返回给定树中最长 交错路径 的长度。
 */
public class LeetCode1372 {
    int res = 0;
    class ReturnType {
        int right;
        int left;
        ReturnType(int left, int right) { // 当前节点为根节点走左边和走右边的最大值
            this.left = left;
            this.right = right;
        }
    }

    public int longestZigZag(TreeNode root) {
        helper(root);
        return res - 1;

    }

    ReturnType helper(TreeNode root) {
        if (root == null) {
            return new  ReturnType(0, 0);
        }

        ReturnType left = helper(root.left);
        ReturnType right = helper(root.right);
        int max = Math.max(left.right + 1, 1 + right.left);
        res = Math.max(res, max);

        return new ReturnType(left.right + 1, 1 + right.left);
    }
}
