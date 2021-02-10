/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.hard;

import com.leetcode.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 功能描述
 *
 * @since 2020-06-16
 */
public class LeetCode297 {
    public String serialize(TreeNode root) {
        return buildSerialize(root, "");
    }

    String buildSerialize(TreeNode root, String str) {
        if (root == null) {
            str += "None";
        } else {
            str += str.valueOf(root.val) + ",";
            str += buildSerialize(root.left, str);
            str += buildSerialize(root.right, str);
        }
        return str;
    }

    public TreeNode rdeserialize(List<String> lists) {
        if (lists.get(0).equals("None")) {
            lists.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(lists.get(0)));
        lists.remove(0);
        root.left = rdeserialize(lists);
        root.right = rdeserialize(lists);
        return root;
    }

    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> lists = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(lists);
    }

    public String serializeBfs(TreeNode root) {
        // tree: [v1,v2,null,...]
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.remove();
            if (cur == null) {
                res.append("null,");
            } else {
                res.append(cur.val + ",");
                queue.add(cur.left);
                queue.add(cur.right);
            }
        }
        res.setLength(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public TreeNode deserializeBfs(String data) {
        String[] nodes = data.substring(1, data.length() - 1).split(",");
        TreeNode root = getNode(nodes[0]);
        Queue<TreeNode> parents = new LinkedList();
        TreeNode parent = root;
        boolean isLeft = true;
        for (int i = 1; i < nodes.length; i++) {
            TreeNode cur = getNode(nodes[i]);
            if (isLeft) {
                parent.left = cur;
            } else {
                parent.right = cur;
            }
            if (cur != null) {
                parents.add(cur);
            }
            isLeft = !isLeft;
            if (isLeft) {
                parent = parents.peek();
                parents.poll();
            }
        }
        return root;
    }

    private TreeNode getNode(String val) {
        if (val.equals("null")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(val));
    }
}
