/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

/**
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
 * 我们通过对数组 A 中的 1 进行深度优先搜索，可以得到两座岛的位置集合，分别为 source 和 target。
 * 随后我们从 source 中的所有位置开始进行广度优先搜索，当它们到达了 target 中的任意一个位置时，搜索的层数就是答案。
 *
 * @since 2020-06-22
 */
public class LeetCode934 {
    public int shortestBridge(int[][] A) {
        int R = A.length, C = A[0].length;
        int[][] colors = getComponents(A);

        Queue<HelperNode> queue = new LinkedList();
        Set<Integer> target = new HashSet();

        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (colors[r][c] == 1) {
                    queue.add(new HelperNode(r, c, 0));
                } else if (colors[r][c] == 2) {
                    target.add(r * C + c);
                }
            }
        }

        while (!queue.isEmpty()) {
            HelperNode node = queue.poll();
            if (target.contains(node.r * C + node.c)) {
                return node.depth - 1;
            }
            for (int nei : neighbors(A, node.r, node.c)) {
                int nr = nei / C, nc = nei % C;
                if (colors[nr][nc] != 1) {
                    queue.add(new HelperNode(nr, nc, node.depth + 1));
                    colors[nr][nc] = 1;
                }
            }
        }

        throw null;
    }

    public int[][] getComponents(int[][] A) {
        int R = A.length, C = A[0].length;
        int[][] colors = new int[R][C];
        int t = 0;

        for (int r0 = 0; r0 < R; ++r0) {
            for (int c0 = 0; c0 < C; ++c0) {
                if (colors[r0][c0] == 0 && A[r0][c0] == 1) {
                    // Start dfs
                    Stack<Integer> stack = new Stack();
                    stack.push(r0 * C + c0);
                    colors[r0][c0] = ++t;

                    while (!stack.isEmpty()) {
                        int node = stack.pop();
                        int r = node / C, c = node % C;
                        for (int nei : neighbors(A, r, c)) {
                            int nr = nei / C, nc = nei % C;
                            if (A[nr][nc] == 1 && colors[nr][nc] == 0) {
                                colors[nr][nc] = t;
                                stack.push(nr * C + nc);
                            }
                        }
                    }
                }
            }
        }

        return colors;
    }

    public List<Integer> neighbors(int[][] A, int r, int c) {
        int R = A.length, C = A[0].length;
        List<Integer> ans = new ArrayList();
        if (0 <= r - 1) {
            ans.add((r - 1) * R + c);
        }
        if (0 <= c - 1) {
            ans.add(r * R + (c - 1));
        }
        if (r + 1 < R) {
            ans.add((r + 1) * R + c);
        }
        if (c + 1 < C) {
            ans.add(r * R + (c + 1));
        }
        return ans;
    }
}

 class HelperNode {
    int r, c, depth;

     HelperNode(int r, int c, int d) {
        this.r = r;
        this.c = c;
        depth = d;
    }

}
