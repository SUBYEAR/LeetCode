package com.leetcode.hard;

import java.util.Arrays;

/**
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 *
 * 注意：1 ≤ k ≤ n ≤ 10^9。
 */
public class LeetCode440_W {
    public int findKthNumber(int n, int k) {
        int cur = 1;
        int prefix = 1;
        while (cur < k) {
            int nums = count(n, prefix); // 获得当前前缀下所有子节点的和
            if (nums + cur > k) { // 第k个数在当前前缀下
                prefix *= 10; // 往下层遍历
                cur++; // 一直遍历到第K个推出循环
            } else {
                prefix++; // 去下个峰头(前缀)遍历
                cur += nums; // 跨过了一个峰(前缀)
            }
        }
        return prefix;
    }

    // 在上界为n的情况下，以prefix为前缀的子节点的个数。用下一个前缀的起点减去当前前缀的起点，那么就是当前前缀下的所有子节点数总和啦。
    private int count(int n, int prefix) {
        long cur = prefix;
        long next = prefix + 1;
        int count = 0;
        while (cur <= n) {
            count += Math.min(n + 1, next) - cur; // 注意这里的n + 1的处理，其实是因为第二层以后0也要算入计数
            // 如果说刚刚prefix是1，next是2，那么现在分别变成10和20
            // 1为前缀的子节点增加10个，十叉树增加一层, 变成了两层
            // 如果说现在prefix是10，next是20，那么现在分别变成100和200，
            // 1为前缀的子节点增加100个，十叉树又增加了一层，变成了三层
            cur *= 10;
            next *= 10;
        }
        return count;
    }
}
