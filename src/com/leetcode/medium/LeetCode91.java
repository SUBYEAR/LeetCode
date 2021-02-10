package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的中序 遍历。 非递归版本
 */
public class LeetCode91 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> s = new Stack<>();

        while (s.isEmpty() || root != null) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }

            root = s.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
