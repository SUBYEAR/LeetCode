/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。
 * 如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * 给你树上某一个节点的标号 label，请你返回从根节点到该标号为 label 节点的路径，该路径是由途经的节点标号所组成的。
 * 输入：label = 14
 * 输出：[1,3,4,14]
 *
 * @since 2020-06-10
 */
public class LeetCode1104_W {
    public List<Integer> pathInZigZagTree(int label) {
        int temp = 1;
        int i = -1;
        while (temp >= 0) {
            ++i;
            temp = label - (1 << i);
        }
        temp = 1;
        int[] arr = new int[(1 << i) - 1]; // i表示完全二叉树的层数
        boolean dir = true;
        int start = 0;
        for (int index = 0; index < i; index++) {
            int[] array = new int[1 << index];
            for (int j = 0; j < (1 << index); j++) {
                array[j] = temp++;
            }
            assign(arr, start, array, dir);
            dir = !dir;
            start += (1 << index);
        }

        LinkedList<Integer> res = new LinkedList<>();
        for (start = 0; start < arr.length; start++) {
            if (arr[start] == label) {
                break;
            }
        }
        while (start > 0) {
            res.addFirst(arr[start]);
            start = (start - 1) / 2;
        }
        res.addFirst(arr[0]);
        return res;
    }

    void assign(int[] arr, int start, int[] sub, boolean dir) {
        for (int i = 0; i < sub.length; i++) {
            arr[start + i] = dir ? sub[i] : sub[sub.length - 1 - i];
        }
    }

    // 到标号为 label 的节点所在的行和该节点的「从左到右标号」.根节点在第一行，偶数行需要转换
    public List<Integer> pathInZigZagTree_(int label) {
        int row = 1, rowStart = 1;
        while (rowStart * 2 <= label) {
            row++;
            rowStart *= 2;
        }
        if (row % 2 == 0) {
            label = getReverse(label, row);
        }
        List<Integer> path = new ArrayList<Integer>();
        while (row > 0) {
            if (row % 2 == 0) {
                path.add(getReverse(label, row));
            } else {
                path.add(label);
            }
            row--;
            label >>= 1;
        }
        Collections.reverse(path);
        return path;
    }

    // 第 i 行有 2^(i−1)个节点，最左边的节点标号为 2^(i−1),最右边的节点标号为 (2^i)-1,偶数行进行反转
    public int getReverse(int label, int row) {
        return (1 << row - 1) + (1 << row) - 1 - label;
    }
}

