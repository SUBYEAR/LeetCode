/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

/**
 * 二叉树上有 n 个节点，按从 0 到 n - 1 编号，其中节点 i 的两个子节点分别是 leftChild[i] 和 rightChild[i]。
 * 只有 所有 节点能够形成且 只 形成 一颗 有效的二叉树时，返回 true；否则返回 false。
 * 如果节点 i 没有左子节点，那么 leftChild[i] 就等于 -1。右子节点也符合该规则。
 * 注意：节点没有值，本问题中仅仅使用节点编号。
 *
 * @since 2020-06-03
 */
public class LeetCode1361 {
    public void validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // ArrayList<Integer> indeg = new ArrayList<>(n);
        // for (int i = 0; i < n; ++i) {
        //     if (leftChild[i] != -1) {
        //         ++indeg[leftChild[i]];
        //     }
        //     if (rightChild[i] != -1) {
        //         ++indeg[rightChild[i]];
        //     }
        // }
        //
        // int root = -1; // 入度为0的点即为根节点
        // for (int i = 0; i < n; ++i) {
        //     if (!indeg[i]) {
        //         root = i;
        //         break;
        //     }
        // }
        // if (root == -1) {
        //     return false;
        // }
        //
        // unordered_set<int> seen;
        // queue<int> q;
        // seen.insert(root);
        // q.push(root);
        //
        // // 一颗有效的二叉树时，所有的节点会恰好被遍历一次。如果某一个节点被遍历了超过一次（有不止一个父节点）或零次（不连通）
        // while (!q.empty()) {
        //     int u = q.front();
        //     q.pop();
        //     if (leftChild[u] != -1) {
        //         if (seen.count(leftChild[u])) {
        //             return false;
        //         }
        //         seen.insert(leftChild[u]);
        //         q.push(leftChild[u]);
        //     }
        //     if (rightChild[u] != -1) {
        //         if (seen.count(rightChild[u])) {
        //             return false;
        //         }
        //         seen.insert(rightChild[u]);
        //         q.push(rightChild[u]);
        //     }
        // }
        //
        // return seen.size() == n;
    }
}
