/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 功能描述
 *
 * @since 2020-08-13
 */


    // Definition for a Node.
    class Node {
        public int val;

        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

public class LeetCode133 {
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        boolean[] mark = new boolean[101];
        Map<Integer, int[]> map = new HashMap<>();

        Queue<Node> q = new LinkedList<>();
        q.add(node);
        mark[node.val] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int[] index = new int[cur.neighbors.size()];
            int i = 0;
            for(Node n : cur.neighbors) {
                index[i++] = n.val;
            }
            map.put(cur.val, index);

            for(Node n : cur.neighbors) {
                if (mark[n.val] == true) {
                    continue;
                }
                mark[n.val] = true;
                q.add(n);
            }
        }

        Node[] nodes = new Node[101];
        for (int key : map.keySet()) {
            nodes[key] = new Node(key);
        }

        for (Map.Entry<Integer, int[]> e : map.entrySet()) {
            Integer key = e.getKey();
            int[] value = e.getValue();
            for (int index : value)
            nodes[key].neighbors.add(nodes[index]);
        }

        return nodes[node.val];
    }
}
