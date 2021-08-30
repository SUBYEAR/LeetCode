package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 *
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-width-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode662 {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Deque<HelperTreeNode> que = new LinkedList<>();
        que.add(new HelperTreeNode(root, 1));

        while (!que.isEmpty()) {
            int size = que.size();
            ans = Math.max(ans, que.peekLast().index - que.peekFirst().index + 1);
            while (size-- > 0) {
                HelperTreeNode cur = que.poll();
                if (cur.node.left != null) {
                    que.offer(new HelperTreeNode(cur.node.left, cur.index * 2));
                }

                if (cur.node.right != null) {
                    que.offer(new HelperTreeNode(cur.node.right, cur.index * 2 + 1));
                }
            }
        }
        return ans;
    }

    private static class HelperTreeNode {
        TreeNode node;
        int index;

        public HelperTreeNode(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }
}
