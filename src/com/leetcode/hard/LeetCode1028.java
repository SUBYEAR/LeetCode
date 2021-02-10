/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.hard;

import com.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 *
 * @since 2020-06-18
 */
public class LeetCode1028 {
    public TreeNode recoverFromPreorder(String S) {
        return helper(new StringBuilder(S));
    }

    TreeNode helper(StringBuilder s) {
        if (s.length() == 0) {
            return null;
        }

        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            ++i;
        }

        TreeNode root = new TreeNode(Integer.parseInt(s.substring(0, i)));
        StringBuilder left = new StringBuilder();
        StringBuilder right = new StringBuilder();
        split(s, left, right);
        if (left.length() > 0) {
            root.left = helper(left);
        }
        if (right.length() > 0) {
            root.right = helper(right);
        }
        return root;
    }

    void split(StringBuilder root, StringBuilder left, StringBuilder right) {
        if (root.length() == 0) {
            return;
        }
        int i = 0;
        int j = 0;
        while (i < root.length() && Character.isDigit(root.charAt(i))) {
            ++i;
        }

        for (j = i + 1; j < root.length() - 1; j++) {
            if (Character.isDigit(root.charAt(j - 1)) && root.charAt(j) == '-'
                && Character.isDigit(root.charAt(j + 1))) {
                break;
            }
        }
        j = j == (root.length() - 1) ? root.length() : j;
        getSubString(root, i, j, left);
        if (left.length() > 0) {
            System.out.println(left);
        }

        getSubString(root, j, root.length(), right);
        if (right.length() > 0) {
            System.out.println(right);
        }

    }

    private void getSubString(StringBuilder s, int start, int end, StringBuilder res) {
        if (start == end) {
            return;
        }
        boolean flag = false;
        for (int index = start + 1; index < end; index++) {
            if (Character.isDigit(s.charAt(index))) {
                res.append(s.charAt(index));
                flag = false;
            } else {
                if (flag) {
                    res.append(s.charAt(index));
                }
                flag = true;
            }
        }
    }

    public TreeNode recoverFromPreorder2(String S) {
        Deque<TreeNode> path = new LinkedList<TreeNode>();
        int pos = 0;
        while (pos < S.length()) {
            int level = 0;
            while (S.charAt(pos) == '-') {
                ++level;
                ++pos;
            }
            int value = 0;
            while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
                value = value * 10 + (S.charAt(pos) - '0');
                ++pos;
            }
            TreeNode node = new TreeNode(value);
            if (level == path.size()) {
                if (!path.isEmpty()) {
                    path.peek().left = node;
                }
            } else {
                while (level != path.size()) {
                    path.pop();
                }
                path.peek().right = node;
            }
            path.push(node);
        }
        while (path.size() > 1) {
            path.pop();
        }
        return path.peek();
    }
}
