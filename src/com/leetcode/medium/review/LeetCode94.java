package com.leetcode.medium.review;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/*
给定一个二叉树，返回它的中序 遍历。 非递归版本
 */
public class LeetCode94 {
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

// Morris 中序遍历
// class Solution {
//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        TreeNode predecessor = null;
//
//        while (root != null) {
//            if (root.left != null) {
//                // predecessor 节点就是当前 root 节点向左走一步，然后一直向右走至无法走为止
//                predecessor = root.left;
//                while (predecessor.right != null && predecessor.right != root) {
//                    predecessor = predecessor.right;
//                }
//
//                // 让 predecessor 的右指针指向 root，继续遍历左子树
//                if (predecessor.right == null) {
//                    predecessor.right = root;
//                    root = root.left;
//                }
//                // 说明左子树已经访问完了，我们需要断开链接
//                else {
//                    res.add(root.val);
//                    predecessor.right = null;
//                    root = root.right;
//                }
//            }
//            // 如果没有左孩子，则直接访问右孩子
//            else {
//                res.add(root.val);
//                root = root.right;
//            }
//        }
//        return res;
//    }
//}
