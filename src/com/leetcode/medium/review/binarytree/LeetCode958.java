/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，确定它是否是一个完全二叉树。
 * 百度百科中对完全二叉树的定义如下：
 * 若设二叉树的深度为 h，除第 h 层外，其它各层 (1～h-1) 的结点数都达到最大个数，
 * 第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。（注：第 h 层可能包含 1~ 2h 个节点。）
 *
 * @since 2020-06-20
 */
public class LeetCode958 {
    public boolean isCompleteTree(TreeNode root) {
        List<ANode> nodes = new ArrayList();
        nodes.add(new ANode(root, 1));
        int i = 0;
        while (i < nodes.size()) {
            ANode anode = nodes.get(i++);
            if (anode.node != null) {
                nodes.add(new ANode(anode.node.left, anode.code * 2));
                nodes.add(new ANode(anode.node.right, anode.code * 2 + 1));
            }
        }

        return nodes.get(i - 1).code == nodes.size();
    }

    private class ANode {  // Annotated Node
        TreeNode node;

        int code;

        ANode(TreeNode node, int code) {
            this.node = node;
            this.code = code;
        }
    }
}

// 当且仅当存在两个相邻节点：前一个为null，后一个不为null时，才不是不是完全二叉树。
// public boolean isCompleteTree(TreeNode root) {
//        Queue<TreeNode> queue = new LinkedList<>();
//        TreeNode prev = root;
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.remove();
//            if (prev == null && node != null)
//                return false;
//            if (node != null) {
//                queue.add(node.left);
//                queue.add(node.right);
//            }
//            prev = node;
//        }
//        return true;
//
