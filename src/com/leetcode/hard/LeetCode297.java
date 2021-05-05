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
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，
 * 同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。请设计一个算法来实现二叉树的序列化与反序列化。
 * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 *
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，
 * 你也可以采用其他的方法解决这个问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @since 2020-06-16
 */
public class LeetCode297 {
    public String serialize(TreeNode root) {
        return buildSerialize(root, "");
    }

    String buildSerialize(TreeNode root, String str) {
        if (root == null) {
            str += "None, ";
        } else {
            str += root.val + ",";
            str = buildSerialize(root.left, str);
            str = buildSerialize(root.right, str);
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
            if (isLeft) { // 注意这里获取获取父节点的处理很巧妙，因为这里是一颗带有null节点的满二叉树
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
