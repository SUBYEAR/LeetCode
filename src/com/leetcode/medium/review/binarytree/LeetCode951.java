/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 我们可以为二叉树 T 定义一个翻转操作，如下所示：选择任意节点，然后交换它的左子树和右子树。
 * 只要经过一定次数的翻转操作后，能使 X 等于 Y，我们就称二叉树 X 翻转等价于二叉树 Y。
 * 编写一个判断两个二叉树是否是翻转等价的函数。这些树由根节点 root1 和 root2 给出。
 *
 * @since 2020-06-27
 */
public class LeetCode951 {
    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        // r1 r2 为null 返回t
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 == null || root2 == null) {
            return false;
        } else if (root1.val != root2.val) {
            return false;
        }

        TreeNode left1 = root1.left;
        TreeNode right1 = root1.right;
        TreeNode left2 = root2.left;
        TreeNode right2 = root2.right;

        boolean b1 = flipEquiv(left1, left2) && flipEquiv(right1, right2);
        boolean b2 = flipEquiv(left1, right2) && flipEquiv(right1, left2);

        return b1 || b2;
    }

//    让树中所有节点的左孩子都小于右孩子，如果当前不满足就翻转。我们把这种状态的二叉树称为 标准态。所有等价二叉树在转换成标准态后都是完全一样的。
    public boolean flipEquiv_dfs(TreeNode root1, TreeNode root2) {
        List<Integer> vals1 = new ArrayList();
        List<Integer> vals2 = new ArrayList();
        dfs(root1, vals1);
        dfs(root2, vals2);
        return vals1.equals(vals2);
    }

    public void dfs(TreeNode node, List<Integer> vals) {
        if (node != null) {
            vals.add(node.val);
            int L = node.left != null ? node.left.val : -1;
            int R = node.right != null ? node.right.val : -1;

            if (L < R) {
                dfs(node.left, vals);
                dfs(node.right, vals);
            } else {
                dfs(node.right, vals);
                dfs(node.left, vals);
            }

            vals.add(null); // 不加这一句也也可
        }
    }

    static void print(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val);
        print(root.left);
        print(root.right);
    }
}
