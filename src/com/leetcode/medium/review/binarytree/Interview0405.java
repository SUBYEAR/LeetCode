/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

/**
 * 面试题 04.05. 合法二叉搜索树
 *
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树。
 *
 * https://leetcode-cn.com/problems/legal-binary-search-tree-lcci/
 *
 * @since 2020-06-27
 */
public class Interview0405 {
    public boolean isValidBST(TreeNode root) {
        helper(root);
        return ans;
    }

    long cur = Long.MIN_VALUE;

    boolean ans = true;

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        int next = root.val;
        if (cur < next && ans) {
            cur = next;
        } else {
            ans = false;
        }
        helper(root.right);
    }

    public boolean isValidBSTPre(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isValidBSThelp(root.left, Long.MIN_VALUE, root.val)
            && isValidBSThelp(root.right, root.val, Long.MAX_VALUE);
    }

    private boolean isValidBSThelp(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        boolean left = isValidBSThelp(root.left, min, root.val);
        boolean right = isValidBSThelp(root.right, root.val, max);
        return left && right;
    }
}
