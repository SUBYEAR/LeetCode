/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 * 示例 1:
 * 输入: 12, 21
 * 输出: 21, -1
 * 思路1: 数值转换成字符串，全排列后找到下一个数----超时
 * 思路2: 我们需要从右往左找到第一对连续的数字 a[i] 和 a[i-1] 满足 a[i-1] < a[i]
 * 要重新排布 a[i-1] 到最右边的数字来得到下一个排列
 * 得到恰好大于当前数字的下一个排列，所以我们需要用恰好大于 a[i−1] 的数字去替换掉 a[i−1]，比方说我们让这个数字为 a[j]，交换这两个数
 * 这时只是a[i -1]是正确情况了，还需要将下标1开始的数字升序排列及为结果
 *
 * @since 2020-06-11
 */
public class LeetCode556 {
    public int nextGreaterElement(int n) {
        List<String> list = new LinkedList<>();
        String str = String.valueOf(n);
        new Permutation().getPermutation(str.toCharArray(), 0, str.length() - 1, list);
        Collections.sort(list);
        System.out.println(list);
        int res = -1;
        if (n == Integer.parseInt(list.get(list.size() - 1))) {
            return res;
        }
        for (String s : list) {
            if (n < Integer.parseInt(s)) {
                res = Integer.parseInt(s);
                break;
            }
        }
        return res;
    }

    public int nextGreaterElement2(int n) {
        char[] a = ("" + n).toCharArray();
        int i = a.length - 2;
        while (i >= 0 && a[i + 1] <= a[i]) {
            i--;
        }
        if (i < 0) {
            return -1;
        }
        int j = a.length - 1;
        while (j >= 0 && a[j] <= a[i]) {
            j--;
        }
        swap(a, i, j);
        reverse(a, i + 1);
        try {
            return Integer.parseInt(new String(a));
        } catch (Exception e) {
            return -1;
        }
    }

    private void reverse(char[] a, int start) {
        int i = start, j = a.length - 1;
        while (i < j) {
            swap(a, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] a, int i, int j) {
        char temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
