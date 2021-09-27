package com.leetcode.medium.review.binarytree;

import com.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 */
public class LeetCode652 {
    Map<String, Integer> count;
    List<TreeNode> res;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        count = new HashMap<>();
        res = new ArrayList<>();

        return res;
    }

    private String collect(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String serial = root.val + "," + collect(root.left) + "," + collect(root.right);
        count.put(serial, count.getOrDefault(serial, 0) + 1);
        if (count.get(serial) == 2) {
            res.add(root);
        }
        return serial;
    }





    public boolean isSame(TreeNode r1, TreeNode r2) {
        if (r1 == null ^ r2 == null) {
            return false;
        }
        if (r1 == null) {
            return true;
        }
        return r1.val == r2.val && isSame(r1.left, r2.left) && isSame(r1.right, r2.right);
    }

    public boolean findSame(TreeNode root, TreeNode target, List<TreeNode> res) {
        if (root == null ^ target == null) {
            return false;
        }
        if (root == null) {
            return true;
        }

        if (root.val == target.val && isSame(root, target)) {
            res.add(root);
            return true;
        }
        boolean b = findSame(root.left, target, res);
        if(!b) b = findSame(root.right, target, res);
        return b;
    }

    void getNodes(TreeNode root, List<TreeNode> list) {
        if (root == null) {
            return;
        }
        list.add(root);
        getNodes(root.left, list);
        getNodes(root.right, list);
    }
}
