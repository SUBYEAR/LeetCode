/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.hard.binaryTree;

import com.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），
 * 然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 *
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 *
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

    /**
     * 记当前节点为 T，上一个节点为 S，那么实际上只有两种情况：
     *
     * T 是 S 的左子节点；
     *
     * T 是根节点到 S 这一条路径上（不包括 S）某一个节点的右子节点。
     *
     * 为什么不包括 S？因为题目中规定了如果节点只有一个子节点，那么保证该子节点为左子节点。
     * 在 T 出现之前，S 仍然还是一个叶节点，没有左子节点，因此 T 如果是 S 的子节点，一定是优先变成 S 的左子节点。
     * 这是因为先序遍历本身的性质。在先序遍历中，我们是通过「根 — 左 — 右」的顺序访问每一个节点的。
     * 想一想先序遍历的递归 + 回溯方法，对于在先序遍历中任意的两个相邻的节点 S 和 T，要么 T 就是 S 的左子节点
     * （对应上面的第一种情况），要么在遍历到 S 之后发现 S 是个叶节点，于是回溯到之前的某个节点，
     * 并开始递归地遍历其右子节点（对应上面的第二种情况）。这样以来，我们按照顺序维护从根节点到当前节点的路径上的所有节点，
     * 就可以方便地处理这两种情况。仔细想一想，这实际上就是使用递归 + 回溯的方法遍历一棵树时，栈中的所有节点，
     * 也就是可以回溯到的节点。因此我们只需要使用一个栈来模拟递归 + 回溯即可。
     */
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
            if (level == path.size()) { //  T 的深度恰好比 S 的深度大 1
                if (!path.isEmpty()) {
                    path.peek().left = node;
                }
            } else {
                while (level != path.size()) { // 注意这里的循环条件其实是使用到了题目中的约束条件：如果节点只有一个子节点，那么保证该子节点为左子节点
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
