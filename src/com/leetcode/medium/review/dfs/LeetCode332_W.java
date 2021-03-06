package com.leetcode.medium.review.dfs;

import java.util.*;

/**
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，
 * 对该行程进行重新规划排序。所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，
 * 所以该行程必须从 JFK 开始。
 *
 * 提示：
 * 如果存在多种有效的行程，请你按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
 * 所有的机场都用三个大写字母表示（机场代码）。
 * 假定所有机票至少存在一种合理的行程。
 * 所有的机票必须都用一次 且 只能用一次。
 */
public class LeetCode332_W {
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) { // 优先级队列保证了题目的自然顺序
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary); // 注意最后要将结果反转
        return itinerary;
    }

    // Hierholzer 算法用于在连通图中寻找欧拉路径，其流程如下：
    //从起点出发，进行深度优先搜索。
    //每次沿着某条边从某个顶点移动到另外一个顶点的时候，都需要删除这条边。
    //如果没有可移动的路径，则将所在节点加入到栈中，并返回。
/*
只有那个入度与出度差为 1 的节点会导致死胡同。而该节点必然是最后一个遍历到的节点。
我们可以改变入栈的规则，当我们遍历完一个节点所连的所有节点后，我们才将该节点入栈（即逆序入栈）。
对于当前节点而言，从它的每一个非「死胡同」分支出发进行深度优先搜索，都将会搜回到当前节点。
而从它的「死胡同」分支出发进行深度优先搜索将不会搜回到当前节点。也就是说当前节点的死胡同分支将会优先于其他非「死胡同」分支入栈。
 */
    public void dfs(String curr) {
        // 可以让死胡同节点先入栈的原因是这个节点不会作为出发点那么这个map中就不含有它，于是它会最先跳出循环入栈
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }

    // 回溯解法
    List<String> res = new ArrayList<>();
    public List<String> findItinerary_backtrack(List<List<String>> tickets) {
        //from,             dest,    number
        Map<String, TreeMap<String, Integer>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);

            map.putIfAbsent(from, new TreeMap<>());
            TreeMap<String, Integer> treeMap = map.get(from);
            treeMap.put(to, treeMap.getOrDefault(to, 0) + 1);
        }

        res.add("JFK");
        backtrack(tickets, map, 0);

        return res;
    }

    private boolean backtrack(List<List<String>> tickets, Map<String, TreeMap<String, Integer>> map, int progress) {
        if (progress == tickets.size()) {
            return true;
        }

        TreeMap<String, Integer> tos = map.get(res.get(res.size() - 1));
        if (tos == null || tos.isEmpty() || tos.size() == 0)
            return false;

        for (String str : tos.keySet()) {
            if (tos.get(str) == 0)
                continue;

            res.add(str);
            tos.put(str, tos.get(str) - 1);

            if (backtrack(tickets, map, progress + 1))
                return true;

            res.remove(res.size() - 1);
            tos.put(str, tos.get(str) + 1);
        }

        return false;
    }
}
