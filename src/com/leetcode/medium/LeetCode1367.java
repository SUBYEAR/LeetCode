/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

import com.leetcode.ListNode;
import com.leetcode.TreeNode;

/**
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，
 * 否则返回 False 。
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 *
 * @since 2020-06-12
 */
public class LeetCode1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }
        return search(head, root) || isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    boolean search(ListNode head, TreeNode root) {
        if (head == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (root.val != head.val) {
            return false;
        }

        return search(head.next, root.left) || search(head.next, root.right);
    }
}
