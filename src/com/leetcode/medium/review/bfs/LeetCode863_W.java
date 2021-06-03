package com.leetcode.medium.review.bfs;

import com.leetcode.TreeNode;

import java.util.*;

/**
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 *
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/all-nodes-distance-k-in-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode863_W {
    HashMap<TreeNode, TreeNode> parent = new HashMap<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        dfs(root, null);
        Queue<TreeNode> q = new LinkedList<>();
        q.add(null); // null 可以看作是一个标记，注意null加在target前和target后的处理是有区别的
        q.add(target);

        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        seen.add(null);

        int dist = 0;
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur == null) {
                if (dist == K) {
                    List<Integer> res = new ArrayList<>();
                    for (TreeNode v : q) {
                        res.add(v.val);
                    }
                    return res;
                }

                q.offer(null);
                dist++;
            } else {
                if (!seen.contains(cur.left)) {
                    seen.add(cur.left);
                    q.offer(cur.left);
                }
                if (!seen.contains(cur.right)) {
                    seen.add(cur.right);
                    q.offer(cur.right);
                }
                TreeNode p = parent.get(cur);
                if (!seen.contains(p)) {
                    seen.add(p);
                    q.offer(p);
                }
            }
        }

        return Collections.emptyList();
    }

    void dfs(TreeNode root, TreeNode pre) {
        if (root != null) {
            parent.put(root, pre);
            dfs(root.left, root);
            dfs(root.right, root);
        }
    }
}
