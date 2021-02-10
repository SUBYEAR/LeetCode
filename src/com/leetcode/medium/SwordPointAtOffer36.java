package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 二叉树转双向链表
 * 思路：中序遍历然后转换，或者直接在中序遍历的时候把指针位置调整好。此时需要两个辅助变量：head和pre
 *
 * https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/
 */
public class SwordPointAtOffer36 {
    public TreeNode treeToDoublyList(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        midOrder(root, list);

        for (int i = 0; i < list.size(); i++) {
            list.get(i).right = i == list.size() - 1 ? list.get(0) : list.get(i + 1);
            list.get(i).left = i == 0 ? list.get(list.size() - 1) : list.get(i -1);
        }

        return list.get(0);
    }

    void midOrder(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }

        midOrder(root.left, list);
        list.add(root);
        midOrder(root.right, list);
    }
}
