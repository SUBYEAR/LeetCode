/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2020-2020. All rights reserved.
 */

package com.leetcode.medium.review;

/**
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 * 示例 1:
 * 输入: 12, 21
 * 输出: 21, -1
 * <p>
 * 思路1: 数值转换成字符串，全排列后找到下一个数----超时
 * <p>
 * 思路2: 我们需要从右往左找到第一对连续的数字 a[i] 和 a[i-1] 满足 a[i-1] < a[i]
 * 要重新排布 a[i-1] 到最右边的数字来得到下一个排列
 * 得到恰好大于当前数字的下一个排列，所以我们需要用恰好大于 a[i−1] 的数字去替换掉 a[i−1]，比方说我们让这个数字为 a[j]，交换这两个数
 * 这时只是a[i -1]是正确情况了，还需要将下标i - 1开始的到最右边的数字升序排列为结果
 *
 * @since 2020-06-11
 */
public class LeetCode556 {
    public int nextGreaterElement(int n) { // 和31题的解法是一致的
        char[] a = ("" + n).toCharArray();
        int i = a.length - 2;
        while (i >= 0 && a[i + 1] <= a[i]) { // 数组降序结束的地方
            i--;
        }
        if (i < 0) {
            return -1;
        }
        // 以{1,5,8,5,7,6,4,3,1}为例
        int j = a.length - 1;
        while (j >= 0 && a[j] <= a[i]) { // 恰好大于 a[i] 的数字去替换掉 a[i]
            j--;
        }
        swap(a, i, j);
        reverse(a, i + 1); // 只需要反转下标从 i 开始到最后的数字，就可以得到下一个字典序最小的排列。
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
