package com.leetcode.medium;

import com.leetcode.TreeNode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

/**
 * 给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。
 * <p>
 * 回想一下：
 * <p>
 * 叶节点 是二叉树中没有子节点的节点
 * 树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
 * 如果我们假定 A 是一组节点 S 的 最近公共祖先，S 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1123 {

    HashMap<TreeNode, TreeNode> parents = new HashMap<>();

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int height = dfs(root, root);
        int level = 0;
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            ++level;
            if (level == height) {
                break;
            }
            while (size-- > 0) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        if (q.size() == 1) {
            return q.poll();
        }
        HashSet<TreeNode> up = new HashSet<>();
        do {
            up.clear();
            for (TreeNode node : q) {
                up.add(parents.get(node));
            }
            q.clear();
            q.addAll(up);
        } while (up.size() != 1);
        TreeNode[] treeNodes = up.toArray(new TreeNode[0]);
        return treeNodes[0];
    }

    private int dfs(TreeNode cur, TreeNode parent) {
        if (cur == null) {
            return 0;
        }

        parents.put(cur, parent);
        int left = dfs(cur.left, cur);
        int right = dfs(cur.right, cur);
        return Math.max(left, right) + 1;
    }

}
