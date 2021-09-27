package com.leetcode.medium.passed.binaryTree;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 *
 * 叶节点 是指没有子节点的节点。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-root-to-leaf-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode129 {
    List<Integer> list = new ArrayList<>();
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return list.stream().reduce(Integer::sum).orElse(0);
    }

    void dfs(TreeNode root, int cur) {
        if (root == null) {
            return;
        }
        int num = root.val + cur * 10;
        if (root.left == null && root.right == null) {
            list.add(num);
        }
        dfs(root.left, num);
        dfs(root.right, num);
    }
}
