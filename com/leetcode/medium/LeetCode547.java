/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium;

/**
 * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 *
 * @since 2020-06-22
 */
public class LeetCode547 {
    public int findCircleNum(int[][] M) {
        int[] arr = new int[M.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int rootI = 0;
        int rootJ = 0;
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            for (int j = i + 1; j < M[0].length; j++) {
                if (M[i][j] == 1) {
                    rootI = findRoot(arr, i);
                    rootJ = findRoot(arr, j);
                    if (rootI == rootJ) {
                        continue;
                    }
                    arr[rootI] = rootJ;
                    count++;
                }
            }
        }

        return M.length - count;
    }

    int findRoot(int[] arr, int index) {
        while (arr[index] != index) {
            index = arr[index];
        }
        return index;
    }

    int unionsearch(int[] pre, int root) { // 查找根结点
        int son, tmp;
        son = root;
        while (root != pre[root]) //
        {
            root = pre[root];
        }
        while (son != root) { // 路径压缩到子节点都直接在根节点下
            tmp = pre[son];
            pre[son] = root;
            son = tmp;
        }
        return root;
    }
}
