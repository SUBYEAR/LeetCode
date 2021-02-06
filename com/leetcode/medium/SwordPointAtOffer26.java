package com.leetcode.medium;

import com.leetcode.TreeNode;

/**
 * 剑指 Offer 26. 树的子结构
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值
 *
 * https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof/
 */
public class SwordPointAtOffer26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) {
            return false;
        }

        return helper(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    boolean helper(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }

        return helper(A.left, B.left) && helper(A.right, B.right);
    }
}
