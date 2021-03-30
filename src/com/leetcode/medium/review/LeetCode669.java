package com.leetcode.medium.review;

import com.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，
 * 使得所有节点的值在[low, high]中。修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。
 * 可以证明，存在唯一的答案。
 *
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trim-a-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class LeetCode669 {
    TreeNode dummyRoot = null;

    public TreeNode trimBST(TreeNode root, int low, int high) {
        int rootVal = root.val;
        TreeNode pre = new TreeNode(-1);
        pre.right = root;
        dummyRoot = pre;
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                int val = root.val;
                if (val < low) {
                    pre.left = root.right;
                    root = root.right;
                } else if (val > high) {
                    pre.right = root.left;
                    root = root.left;
                } else {
                    stack.push(root);
                    pre = root;
                    root = root.left;
                }
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                pre = root;
                root = root.right;
            }
        }
        int val = dummyRoot.right == null ? -1 : dummyRoot.right.val;
        if (val >= low && val <= high) {
            return dummyRoot.right;
        } else {
            return dummyRoot.left;
        }
    }
}

// 官方解法 递归版本
// class Solution {
//    public TreeNode trimBST(TreeNode root, int L, int R) {
//        if (root == null) return root;
//        if (root.val > R) return trimBST(root.left, L, R);
//        if (root.val < L) return trimBST(root.right, L, R);
//
//        root.left = trimBST(root.left, L, R);
//        root.right = trimBST(root.right, L, R);
//        return root;
//    }
//}
//
