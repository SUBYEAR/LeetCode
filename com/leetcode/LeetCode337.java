/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
 * 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * @since 2020-08-05
 */
public class LeetCode337 {

    class ReturnType {
        int choose;
        int noChoose;

        ReturnType(int choose, int noChoose) {
            this.choose = choose;
            this.noChoose = noChoose;
        }
    }

    public int rob(TreeNode root) {
        if (root == null) return 0;
        ReturnType res = process(root);
        return Math.max(res.choose, res.noChoose);

    }

    ReturnType process (TreeNode root) {
        if (root == null) {
            return new ReturnType(0, 0);
        }
        ReturnType left = process(root.left);
        ReturnType right = process(root.right);
        int choose = root.val + left.noChoose + right.noChoose;


        int a = Math.max(left.choose + right.choose, left.noChoose + right.noChoose);
        int b = Math.max(left.choose + right.noChoose, left.noChoose + right.choose);
        int noChoose = Math.max(a, b);
        System.out.println(choose + ", " + noChoose);
        return new ReturnType(choose, noChoose);
    }


}
