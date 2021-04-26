package com.leetcode.easy;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵二叉搜索树，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 */
public class LeetCode897 {
    List<TreeNode> l = new ArrayList<>();
    public TreeNode increasingBST(TreeNode root) {
        inorder(root);
        l.get(l.size() - 1).left = null;
        l.get(l.size() - 1).right = null;
        for (int i = l.size() - 1; i > 0; i--) {
            l.get(i - 1).left = null;
            l.get(i - 1).right = l.get(i);
        }
        return l.get(0);
    }

    void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        l.add(root);
        inorder(root.right);
    }
}
