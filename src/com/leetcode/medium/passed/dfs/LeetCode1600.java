package com.leetcode.medium.passed.dfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
 *
 * 这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。我们定义递归函数 Successor(x, curOrder) ，给定一个人 x 和
 * 当前的继承顺序，该函数返回 x 的下一继承人。
 *
 * Successor(x, curOrder):
 *     如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：
 *         如果 x 是国王，那么返回 null
 *         否则，返回 Successor(x 的父亲, curOrder)
 *     否则，返回 x 不在 curOrder 中最年长的孩子
 * 比方说，假设王国由国王，他的孩子 Alice 和 Bob （Alice 比 Bob 年长）和 Alice 的孩子 Jack 组成。
 *
 * 一开始， curOrder 为 ["king"].
 * 调用 Successor(king, curOrder) ，返回 Alice ，所以我们将 Alice 放入 curOrder 中，得到 ["king", "Alice"] 。
 * 调用 Successor(Alice, curOrder) ，返回 Jack ，所以我们将 Jack 放入 curOrder 中，得到 ["king", "Alice", "Jack"] 。
 * 调用 Successor(Jack, curOrder) ，返回 Bob ，所以我们将 Bob 放入 curOrder 中，得到 ["king", "Alice", "Jack", "Bob"] 。
 * 调用 Successor(Bob, curOrder) ，返回 null 。最终得到继承顺序为 ["king", "Alice", "Jack", "Bob"] 。
 * 通过以上的函数，我们总是能得到一个唯一的继承顺序。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/throne-inheritance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1600 {
    HelperNode root;
    Map<String, HelperNode> strToNode = new HashMap<>();
    Map<HelperNode, LinkedList<HelperNode>> info = new HashMap<>();
    public LeetCode1600(String kingName) {
        root = new HelperNode(kingName);
        info.put(root, new LinkedList<>());
        strToNode.put(kingName, root);
    }

    public void birth(String parentName, String childName) {
        HelperNode parentNode = strToNode.getOrDefault(parentName, null);
        if (parentNode == null) {
            return;
        }
        LinkedList<HelperNode> children = info.getOrDefault(parentNode, new LinkedList<>());
        HelperNode child = new HelperNode(childName);
        children.add(child);
        info.put(parentNode, children);
        strToNode.put(childName, child);
    }

    public void death(String name) {
        HelperNode node = strToNode.getOrDefault(name, null);
        if (node == null) {
            return;
        }

        node.isLived = false;
    }

    public List<String> getInheritanceOrder() {
        List<String> res = new LinkedList<>();
        Map<HelperNode, Boolean> visit = new HashMap<>();
        dfs(root, res, visit);
        return res;
    }

    private void dfs(HelperNode node, List<String> res, Map<HelperNode, Boolean> visit) {
        if (visit.getOrDefault(node, false)) {
            return;
        }

        visit.put(node, true);
        if (node.isLived) {
            res.add(node.name);
        }
        for (HelperNode child : info.getOrDefault(node, new LinkedList<>())) {
            dfs(child, res, visit);
        }
    }

//    private HelperNode search(String name) {
//        Map<HelperNode, Boolean> visit = new HashMap<>();
//        visit.put(root, true);
//        Queue<HelperNode> que = new LinkedList<>();
//        que.add(root);
//        while (!que.isEmpty()) {
//            HelperNode cur = que.poll();
//            if (name.equals(cur.name)) {
//                return cur;
//            }
//
//            for (HelperNode child : cur.children) {
//                if (visit.getOrDefault(child, false)) {
//                    continue;
//                }
//
//                visit.put(child, true);
//                que.add(child);
//            }
//        }
//        return null;
//    }

    private static class HelperNode {
        private String name;
        private boolean isLived;

        public HelperNode(String name) {
            this.name = name;
            isLived = true;
        }
    }
}
