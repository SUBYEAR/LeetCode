package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
 *
 * 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
 *
 * 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
 *
 * 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-one-row-to-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode623 {
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 1) {
            return new TreeNode(val, root, null);
        }

        int level = 0;
        Queue<HelperNode> q = new LinkedList<>();
        q.add(new HelperNode(root, null, true));
        while (!q.isEmpty()) {
            int size = q.size();
            ++level;
            if (level == depth) {
                break;
            }
            while (size-- > 0) {
                HelperNode cur = q.poll();
                if (cur.node.left != null) {
                    q.add(new HelperNode(cur.node.left, cur.node, true));
                }
                if (cur.node.right != null) {
                    q.add(new HelperNode(cur.node.right, cur.node, false));
                }
            }
        }

        TreeNode lastParent = null;
        for(HelperNode n : q) {
            TreeNode insert = new TreeNode(val);
            if (lastParent != n.parent) {
                n.parent.left = insert;
                n.parent.right = insert;
            }
            lastParent = n.parent;
            if (n.left) {
                insert.left = n.node;
            } else {
                insert.right = n.node;
            }
        }
        return root;
    }

    private class HelperNode {
        TreeNode node;
        TreeNode parent;
        boolean left;

        public HelperNode(TreeNode node, TreeNode parent, boolean left) {
            this.parent = parent;
            this.node = node;
            this.left = left;
        }
    }
}
