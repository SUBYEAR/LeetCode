/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

/**
 * 有两位极客玩家参与了一场「二叉树着色」的游戏。游戏中，给出二叉树的根节点 root，树上总共有 n 个节点，且 n 为奇数，其中每个节点上的值从 1 到 n 各不相同。
 * 游戏从「一号」玩家开始（「一号」玩家为红色，「二号」玩家为蓝色），最开始时，
 * 「一号」玩家从 [1, n] 中取一个值 x（1 <= x <= n）；
 * 「二号」玩家也从 [1, n] 中取一个值 y（1 <= y <= n）且 y != x。
 * 「一号」玩家给值为 x 的节点染上红色，而「二号」玩家给值为 y 的节点染上蓝色。
 * 之后两位玩家轮流进行操作，每一回合，玩家选择一个他之前涂好颜色的节点，将所选节点一个 未着色 的邻节点（即左右子节点、或父节点）进行染色。
 * 如果当前玩家无法找到这样的节点来染色时，他的回合就会被跳过。
 * 若两个玩家都没有可以染色的节点时，游戏结束。着色节点最多的那位玩家获得胜利 ✌️。
 * 现在，假设你是「二号」玩家，根据所给出的输入，假如存在一个 y 值可以确保你赢得这场游戏，则返回 true；若无法获胜，就请返回 false。
 * 1、取胜的关键是取得一半以上的节点数量，另外数字是无序的，随便填写，所以不考虑数字顺序。总共有n个节点（n为奇数），考虑其一半m = n / 2.
 * 2、一个节点，如果取它的左（右）节点，则左（右）节点的所有节点都是你的。取父节点，则父节点以上的所有节点也都是你的。
 * 3、假设一号玩家选取了x节点，其左节点的节点总数为left，右节点的节点总数为right。考虑一下情况：
 * a. left > m 或者 right > m, 你只要选取大于一半的那个节点，则必赢
 * b. left == m 或者 right == m, 无论选取哪个节点，其他另一边节点和父节点都是一号玩家的，一号玩家总数超过一半，都是必输
 * c. m <= left + right < n, 无论选取哪个节点，其他另一边节点和父节点都是一号玩家的，一号玩家总数超过一半，都是必输
 * d. left + right < m, 选取父节点，则总数超过一半，则必赢
 *
 * @since 2020-06-27
 */
public class LeetCode1145_W {
    private int left;

    private int right;

    private int num;

    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        num = x;
        dfs(root);
        int half = n / 2;
        if (left > half || right > half || (left + right) < half) {
            return true;
        } else {
            return false;
        }
    }

    private int dfs(TreeNode node) {
        int leftNum = 0;
        int rightNum = 0;
        if (node.left != null) {
            leftNum = dfs(node.left);
        }
        if (node.right != null) {
            rightNum = dfs(node.right);
        }
        if (node.val == num) {
            left = leftNum;
            right = rightNum;
        }
        return leftNum + rightNum + 1;
    }
}
