package com.leetcode.medium.review.hashmap;

import java.util.*;

/**
 * 存在一个由 n 个不同元素组成的整数数组 nums ，但你已经记不清具体内容。好在你还记得 nums 中的每一对相邻元素。
 *
 * 给你一个二维整数数组 adjacentPairs ，大小为 n - 1 ，其中每个 adjacentPairs[i] = [ui, vi] 表示元素 ui 和 vi 在 nums 中相邻。
 *
 * 题目数据保证所有由元素 nums[i] 和 nums[i+1] 组成的相邻元素对都存在于 adjacentPairs 中，存在形式可能是 [nums[i], nums[i+1]] ，
 * 也可能是 [nums[i+1], nums[i]] 。这些相邻元素对可以 按任意顺序 出现。
 *
 * 返回 原始数组 nums 。如果存在多种解答，返回 其中任意一个 即可。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/restore-the-array-from-adjacent-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1743_W {
    public int[] restoreArray(int[][] adjacentPairs) {
        Map<Integer, List<Integer>>  map = new HashMap<>();
        for (int[] adj : adjacentPairs) {
            map.putIfAbsent(adj[0], new ArrayList<>());
            map.putIfAbsent(adj[1], new ArrayList<>());
            map.get(adj[0]).add(adj[1]);
            map.get(adj[1]).add(adj[0]);
        }

        int n = adjacentPairs.length + 1;
        int[] ret = new int [n];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int e =entry.getKey();
            if (entry.getValue().size() == 1) {
                ret[0] = e;
                break;
            }
        }
        ret[1] = map.get(ret[0]).get(0);
        for (int i = 2; i < n; i++) { // 邻居链表的大小要么是1要么2，如果是中间节点其值为2
            List<Integer> adj = map.get(ret[i - 1]);
            ret[i] = ret[i - 2] == adj.get(0) ? adj.get(1) : adj.get(0);
        }
        return ret;
    }
}
