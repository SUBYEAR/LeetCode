package com.leetcode.medium;

import java.util.*;

/*
给你无向 连通 图中一个节点的引用，请你返回该图的 深拷贝（克隆）。

图中的每个节点都包含它的值 val（int） 和其邻居的列表（list[Node]）。

class Node {
    public int val;
    public List<Node> neighbors;
}

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/clone-graph
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


class LeetCode133 {

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