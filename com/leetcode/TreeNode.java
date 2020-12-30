/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 功能描述
 *
 * @since 2020-06-09
 */
public class TreeNode {
    int val;

    TreeNode left;

    TreeNode right;

    TreeNode(int num) {
        val = num;
    }

    TreeNode() {
        val = 0;
    }

    // public static void print(TreeNode root) {
    // if (root == null) {
    // return;
    // }
    // System.out.print(root.val);
    // print(root.left);
    // print(root.right);
    // }
}
