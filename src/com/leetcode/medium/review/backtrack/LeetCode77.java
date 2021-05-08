package com.leetcode.medium.review.backtrack;

import java.util.*;

/**
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 */
public class LeetCode77 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1, new LinkedList<>());
        return res;
    }

    void backtrack(int n, int k, int cur, List<Integer> path) {
        if (path.size() + n - cur + 1 < k) {
            return;
        }

        if (path.size() == k) {
            res.add(new LinkedList<>(path));
            return;
        }

        for (int i = cur; i <= n; i++) {
            path.add(i);
            backtrack(n, k, i + 1, path);
            path.remove(path.size() - 1);
        }
    }

    // 剪枝优化
    public List<List<Integer>> combine_(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
    }

    private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
        }
    }

//    作者：liweiwei1419
//    链接：https://leetcode-cn.com/problems/combinations/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-ma-/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
