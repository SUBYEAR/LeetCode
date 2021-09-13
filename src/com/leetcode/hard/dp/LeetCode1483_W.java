package com.leetcode.hard.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 *
 * 请你设计并实现 getKthAncestor(int node, int k) 函数，函数返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 *
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 * 提示：
 *
 * 1 <= k <= n <= 5*10^4
 * parent[0] == -1 表示编号为 0 的节点是根节点。
 * 对于所有的 0 < i < n ，0 <= parent[i] < n 总成立
 * 0 <= node < n
 * 至多查询 5*10^4 次
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-ancestor-of-a-tree-node
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode1483_W {
    // dp[node][j] 存储的是 node 节点距离为 2^j 的祖先是谁
    // 根据定义，dp[node][0] 就是 parent[node]，即 node 的距离为 1 的祖先是 parent[node]。
    // 状态转移是： dp[node][j] = dp[dp[node][j - 1]][j - 1]。

    // TreeAncestor
    List<Integer>[] dp;
    public LeetCode1483_W(int n, int[] parent) {
        this.dp = new List[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();// 初始化dp
            dp[i].add(parent[i]); // dp[i][0] = i的第2^0 个父亲节点 = i的最近的父亲节点 = parent[i]
        }
        for (int j = 1; ; j++) {
            boolean allNeg = true; // 判断 节点0 ，2 ....n-1的第2^j次方个父亲节点是否都为-1
            for (int i = 0; i < n; i++) {
                // 对于 节点i的 第 2^j 个父亲节点
                // 如果 ： i 的 第 2^(j-1) 个父亲节点已经是-1了
                //          那么 也将 i 的 第 2^j个父节点也设置为 -1;
                //           -1
                // 否则 ： 将i的第2^j 个父节点 设置为 i的第2^(j-1)个父亲节点的第2^(j-1)个父亲节点
                //           dp[dp[i].get(j-1)].get(j-1);
                int t = dp[i].get(j - 1) == -1 ? -1 : dp[dp[i].get(j - 1)].get(j - 1);
                dp[i].add(t);
                if (t != -1) {
                    allNeg = false;
                }
            }
            if (allNeg) { // 所有节点的 第2^j次方个父亲节点都已经设置为 -1 . 跳出循环
                break;
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int res = node, pos = 0;
        while (k != 0 && res != -1) {
            if (pos >= dp[res].size()){
                return -1;
            }
            if ((k & 1) != 0) {
                res = dp[res].get(pos);
            }
            k >>= 1;
            pos++;
        }
        return res;
    }

    //        链接：https://leetcode-cn.com/problems/kth-ancestor-of-a-tree-node/solution/li-kou-zai-zhu-jian-ba-acm-mo-ban-ti-ban-shang-lai/
}
