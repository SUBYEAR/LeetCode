package com.leetcode.medium.review;

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
    private class Node {
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

// 官方题解
//class Solution {
//    public Node cloneGraph(Node node) {
//        if (node == null) {
//            return node;
//        }
//
//        HashMap<Node, Node> visited = new HashMap(); // 哈希表 visited 存储所有已被访问和克隆的节点
//
//        // 将题目给定的节点添加到队列
//        LinkedList<Node> queue = new LinkedList<Node> ();
//        queue.add(node);
//        // 克隆第一个节点并存储到哈希表中
//        visited.put(node, new Node(node.val, new ArrayList()));
//
//        // 广度优先搜索
//        while (!queue.isEmpty()) {
//            // 取出队列的头节点
//            Node n = queue.remove();
//            // 遍历该节点的邻居
//            for (Node neighbor: n.neighbors) {
//                if (!visited.containsKey(neighbor)) {
//                    // 如果没有被访问过，就克隆并存储在哈希表中
//                    visited.put(neighbor, new Node(neighbor.val, new ArrayList()));
//                    // 将邻居节点加入队列中
//                    queue.add(neighbor);
//                }
//                // 更新当前节点的邻居列表
//                visited.get(n).neighbors.add(visited.get(neighbor));
//            }
//        }
//
//        return visited.get(node);
//    }
//}
