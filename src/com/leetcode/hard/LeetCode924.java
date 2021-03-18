package com.leetcode.hard;

import com.leetcode.UF;

import java.util.Arrays;

/**
 * 在节点网络中，只有当 graph[i][j] = 1 时，每个节点 i 能够直接连接到另一个节点 j。
 * 一些节点 initial 最初被恶意软件感染。只要两个节点直接连接，且其中至少一个节点受到恶意软件的感染，
 * 假设 M(initial) 是在恶意软件停止传播之后，整个网络中感染恶意软件的最终节点数。
 * 我们可以从初始列表中删除一个节点。如果移除这一节点将最小化 M(initial)， 则返回该节点。如果有多个节点满足条件，
 * 就返回索引最小的节点。
 *
 * 请注意，如果某个节点已从受感染节点的列表 initial 中删除，它以后可能仍然因恶意软件传播而受到感染。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimize-malware-spread
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode924 {
    public int minMalwareSpread(int[][] graph, int[] initial) {
        int n = graph.length;
        UF uf = new UF(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j] == 1) {
                    uf.unite(i, j);
                }
            }
        }

        Arrays.sort(initial); // 删除的思路应该是一个连通集中只有一个节点感染时删除它
        int len = initial.length;
        int[] freq = new int[301]; // 找出数组中只出现一次的数字
        int[] root = new int[len];
        for (int i = 0; i < len; i++) {
            root[i] = uf.find(initial[i]);
            freq[root[i]]++;
        }

        int index = 0;
        int last = 0;
        for (int i = 0; i < len; i++) {
            if (freq[root[i]] == 1) {
                if (uf.getSize(root[i]) > last) {
                    index = i;
                    last = uf.getSize(root[i]);
                }
            }
        }

        return initial[index];
    }
}
