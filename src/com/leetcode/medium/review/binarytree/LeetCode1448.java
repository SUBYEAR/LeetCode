package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一棵根为 root 的二叉树，请你返回二叉树中好节点的数目。
 *
 * 「好节点」X 定义为：从根到该节点 X 所经过的节点中，没有任何节点的值大于 X 的值。
 */
public class LeetCode1448 {
    int res = 1; // 根节点肯定是好节点，数的节点是对于0的

    public int goodNodes(TreeNode root) {
        pre(root.left, root);
        pre(root.right, root);
        return res;
    }

    void pre(TreeNode root, TreeNode maxNode) {
        if (root == null) {
            return;
        }
        if (root.val >= maxNode.val) {
            maxNode = root;
            ++res;
        }

        pre(root.left, maxNode);
        pre(root.right, maxNode);
    }
}
